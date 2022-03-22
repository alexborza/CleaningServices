package com.cleaning.facade;

import com.cleaning.entity.*;
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

    public List<EmployeesDayAgenda> getEmployeesAgendaForDate(String date){
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(e -> toEmployeeDayAgenda(e, date))
                .collect(Collectors.toList());
    }

    public List<CleaningServiceDto> getEmployeeCleaningServicesForDate(Long id, String date) {
        List<CleaningService> cleaningServices = cleaningServiceRepository.getEmployeeCleaningServicesForDate(id, date);
        return cleaningServices.stream()
                .filter(cs -> filterDeletedCleaningServices(cs, date))
                .map(cleaningServiceMapper::toCleaningServiceDto)
                .collect(Collectors.toList());
    }

    private boolean filterDeletedCleaningServices(CleaningService cleaningService, String date){
        if(cleaningService.getStatus() == CleaningStatus.Deleted){
            List<CleaningDate> futureCleaningDates = cleaningService.getDatesOfCleaning();
            Optional<CleaningDate> futureCleaningDate = futureCleaningDates.stream()
                    .filter(fc -> fc.getCleaningDate().equals(date))
                    .findFirst();
            if(futureCleaningDate.isPresent())
                return true;
            return false;
        }
        return true;
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
                .filter(fs -> fs.getStatus() != CleaningStatus.Deleted && !fs.getCleaningDate().getCleaningDate().equals(day.getDate()))
                .forEach(fs -> this.addBookedInterval(day, fs));
        return new EmployeesDayAgenda(employee.getId(), day.getAvailableIntervals());
    }

    private void addBookedInterval(Day day, CleaningService cleaningService){
        CleaningDate cleaningDate = cleaningService.getCleaningDate();
        day.addBookedInterval(new BookedInterval(cleaningDate.getStartingHour(), cleaningDate.getFinishingHour(), null));
    }

    private EmployeesDayAgenda createEmptyDayAgenda(Employee employee){
        return new EmployeesDayAgenda(employee.getId(), new ArrayList<>(List.of(new AvailableInterval(9, 17))));
    }
}
