package com.cleaning.facade;

import com.cleaning.entity.*;
import com.cleaning.facade.dto.*;
import com.cleaning.facade.mapper.CleaningServiceMapper;
import com.cleaning.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CleaningServiceFacade {

    @Autowired
    private CleaningServiceRepository repo;

    @Autowired
    private CleaningServiceMapper mapper;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository<User> userRepository;

    public void createCleaningService(Long employeeId, Long userId, CleaningServiceDto cleaningServiceDto){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(EntityNotFoundException::new);
        setBookedIntervalToEmployeeAgenda(employee, cleaningServiceDto.getCleaningDate());
        createCleaningService(employee, cleaningServiceDto, userId);
    }

    public List<CleaningServiceDto> getCleaningServices(){
        List<CleaningService> cleaningServices = (List<CleaningService>) repo.findAll();
        return cleaningServices.stream()
                .map(mapper::toCleaningServiceDto)
                .collect(Collectors.toList());
    }

    public CleaningServiceDto getCleaningService(Long id){
        CleaningService cleaningService = repo.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.toCleaningServiceDto(cleaningService);
    }

    private void setBookedIntervalToEmployeeAgenda(Employee employee, CleaningDateDto cleaningDateDto){
        BookedInterval bookedInterval = getBookedInterval(cleaningDateDto);
        Agenda employeeAgenda = employee.getAgenda();
        setBookedIntervalToEmployeeAgenda(bookedInterval, employeeAgenda, employee, cleaningDateDto.getCleaningDate());
    }

    private BookedInterval getBookedInterval(CleaningDateDto cleaningDateDto){
        return new BookedInterval(cleaningDateDto.getStartingHour(), cleaningDateDto.getFinishingHour(), null);
    }

    private void setBookedIntervalToEmployeeAgenda(BookedInterval bookedInterval, Agenda agenda, Employee employee, String cleaningDate){
        if(agenda == null){
            createAgendaForEmployee(bookedInterval, employee, cleaningDate);
        } else {
            Optional<Day> day = findDayInAgenda(agenda, cleaningDate);
            if(day.isEmpty()){
                createNewDay(agenda, bookedInterval, cleaningDate);
            } else {
                Day existingDay = day.get();
                existingDay.addBookedInterval(bookedInterval);
            }
        }
    }

    private void createAgendaForEmployee(BookedInterval bookedInterval, Employee employee, String cleaningDate){
        Agenda agenda = new Agenda();
        createNewDay(agenda, bookedInterval, cleaningDate);
        agenda.setEmployee(employee);
        employee.setAgenda(agenda);
    }

    private void createNewDay(Agenda agenda, BookedInterval bookedInterval, String cleaningDate){
        Day newDay = new Day();
        newDay.setDate(cleaningDate);
        newDay.addBookedInterval(bookedInterval);
        newDay.setAgenda(agenda);
        agenda.addDay(newDay);
    }

    private Optional<Day> findDayInAgenda(Agenda agenda, String cleaningDate){
        return agenda.getDays().stream()
                .filter(d -> d.getDate().equals(cleaningDate))
                .findFirst();
    }

    private void createCleaningService(Employee employee, CleaningServiceDto cleaningServiceDto, Long userId){
        if(userId == null) {
            createCleaningServiceWithNoClient(employee, cleaningServiceDto);
        } else {
            Client client = clientRepository.findById(userId)
                    .orElseThrow(EntityNotFoundException::new);
            createCleaningServiceWithClient(employee, client, cleaningServiceDto);
        }
    }

    private void createCleaningServiceWithNoClient(Employee employee, CleaningServiceDto cleaningServiceDto){
        CleaningService cleaningService = mapper.toCleaningServiceEntity(cleaningServiceDto);
        employee.addCleaningService(cleaningService);
        cleaningService.setEmployee(employee);
        employeeRepository.save(employee);
    }

    private void createCleaningServiceWithClient(Employee employee, Client client, CleaningServiceDto cleaningServiceDto){
        CleaningService cleaningService = mapper.toCleaningServiceEntity(cleaningServiceDto);
        client.addCleaningService(cleaningService);
        cleaningService.setClient(client);
        employee.addCleaningService(cleaningService);
        cleaningService.setEmployee(employee);
        userRepository.saveAll(List.of(client, employee));
    }
}
