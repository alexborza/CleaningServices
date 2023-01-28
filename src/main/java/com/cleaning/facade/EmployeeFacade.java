package com.cleaning.facade;

import com.cleaning.entity.appointment.*;
import com.cleaning.entity.cleaning_service.*;
import com.cleaning.entity.users.*;
import com.cleaning.facade.dto.*;
import com.cleaning.facade.mapper.*;
import com.cleaning.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.*;

@Service
public class EmployeeFacade {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CleaningServiceRepository cleaningServiceRepository;

    @Autowired
    private CleaningServiceMapper cleaningServiceMapper;

    @Autowired
    private AdministratorMapper administratorMapper;

    public void modifyEmergencyContactInfo(Long userId, EmergencyContactInformationDto dto){
        Employee employee = employeeRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
        EmployeeInformation employeeInformation = employee.getEmployeeInformation();
        employeeInformation.setEmergencyContactInformation(administratorMapper.emergencyContactInfoDtoToEntity(dto));
        employeeRepository.save(employee);
    }

    public void modifyJobInfo(Long userId, JobInformationDto dto){
        Employee employee = employeeRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
        EmployeeInformation employeeInformation = employee.getEmployeeInformation();
        employeeInformation.setJobInformation(administratorMapper.jobInformationDtoToEntity(dto));
        employeeRepository.save(employee);
    }

    public EmployeeDto getEmployee(Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return administratorMapper.toEmployeeDto(employee);
    }

    public List<EmployeesDayAgenda> getEmployeesAgendaForDate(String date, String frequency){
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeesDayAgenda> employeesDayAgenda = employees.stream()
                .map(e -> toEmployeeDayAgenda(e, date))
                .collect(Collectors.toList());
        calculateAvailableIntervalsForOverlapping(employeesDayAgenda, employees, date, frequency);
        return employeesDayAgenda;
    }

    public List<CleaningServiceDto> getEmployeeCleaningServicesForDate(Long id, String date) {
        List<CleaningService> cleaningServices = cleaningServiceRepository.getEmployeeCleaningServicesForDate(id, date);
        mapCleaningServiceDate(cleaningServices, date);
        return cleaningServices.stream()
                .distinct()
                .filter(cs -> filterDeletedCleaningServices(cs, date))
                .filter(cs -> filterRescheduledDates(cs,date))
                .sorted(Comparator.comparingInt(CleaningService::getStartingHour))
                .map(cleaningServiceMapper::toCleaningServiceDto)
                .collect(Collectors.toList());
    }

    private void mapCleaningServiceDate(List<CleaningService> cleaningServices, String date){
        cleaningServices.forEach(cs -> cs.setCleaningDate(cs.getDateOfCleaning(date)));
    }

    private void calculateAvailableIntervalsForOverlapping(List<EmployeesDayAgenda> employeesDayAgenda, List<Employee> employees, String date, String frequency){
        employeesDayAgenda.forEach(employeeDayAgenda -> {
            List<BookedInterval> bookedIntervals = new ArrayList<>();
            Employee employee = employees.stream()
                    .filter(e -> Objects.equals(e.getId(), employeeDayAgenda.getEmployeeId()))
                    .findFirst()
                    .orElse(null);
            employee.getFrequentServices(date, frequency).forEach(cs -> {
                BookedInterval bookedInterval = new BookedInterval(cs.getStartingHour(), cs.getFinishingHour());
                bookedIntervals.add(bookedInterval);
            });
            calculateEmployeeAvailableIntervalsForOverlapping(employeeDayAgenda, bookedIntervals);
        });
    }

    private void calculateEmployeeAvailableIntervalsForOverlapping(EmployeesDayAgenda employeesDayAgenda, List<BookedInterval> bookedIntervals){
        if(!bookedIntervals.isEmpty()){
            Day day = Day.dayWithOverlappingIntervals(employeesDayAgenda.getTimeSlots(), bookedIntervals);
            day.calculateAvailableIntervalsForOverlappingIntervals();
            employeesDayAgenda.setAvailableIntervalsForOverlapping(day.getAvailableIntervals());
        }
    }

    private boolean filterDeletedCleaningServices(CleaningService cleaningService, String date){
        if(cleaningService.getStatus() == AppointmentStatus.Deleted){
            List<CleaningDate> datesOfCleaning = cleaningService.getDatesOfCleaning();
            return datesOfCleaning.stream()
                    .anyMatch(fc -> fc.getCleaningDate().equals(date));
        }
        return true;
    }

    private boolean filterRescheduledDates(CleaningService cleaningService, String date) {
        return !cleaningService.isDateRescheduled(date);
    }

    private EmployeesDayAgenda toEmployeeDayAgenda(Employee employee, String date){
        if(employee.getAgenda() == null){
            return createEmptyDayAgenda(employee);
        }
        Agenda agenda = employee.getAgenda();
        Day day = getDayForDate(agenda, date);
        day.setDate(date);
        return createDayAgenda(employee, day);
    }

    private Day getDayForDate(Agenda agenda, String date){
        return agenda.getDays().stream()
                .filter(d -> d.getDate().equals(date))
                .findFirst()
                .orElse(Day.create(date));
    }

    private EmployeesDayAgenda createDayAgenda(Employee employee, Day day){
        List<CleaningService> frequentServices = cleaningServiceRepository.getEmployeeCleaningServicesForDate(employee.getId(), day.getDate());
        frequentServices.stream()
                .distinct()
                .filter(fs -> this.filterDeletedCleaningServices(fs, day.getDate()))
                .filter(fs -> this.filterRescheduledDates(fs, day.getDate()))
                .forEach(fs -> this.addBookedInterval(day, fs));
        return new EmployeesDayAgenda(employee.getId(), day.getAvailableIntervals());
    }

    private void addBookedInterval(Day day, CleaningService cleaningService){
        CleaningDate cleaningDate = cleaningService.getDateOfCleaning(day.getDate());
        day.addBookedInterval(new BookedInterval(cleaningDate.getStartingHour(), cleaningDate.getFinishingHour()));
    }

    private EmployeesDayAgenda createEmptyDayAgenda(Employee employee){
        return new EmployeesDayAgenda(employee.getId(), new ArrayList<>(List.of(new TimeSlot(9, 17))));
    }
}
