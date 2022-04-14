package com.cleaning.facade;

import com.cleaning.entity.*;
import com.cleaning.facade.dto.*;
import com.cleaning.facade.mapper.*;
import com.cleaning.facade.vo.*;
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

    @Autowired
    private CleaningServiceDescriptionRepository cleaningServiceDescriptionRepository;

    @Autowired
    private CleaningServiceDescriptionMapper cleaningServiceDescriptionMapper;

    @Autowired
    private CleaningServicePricesRepository cleaningServicePricesRepository;

    @Autowired
    private CleaningServicePricesMapper cleaningServicePricesMapper;

    public void createCleaningService(Long employeeId, Long userId, CleaningServiceDto cleaningServiceDto){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(EntityNotFoundException::new);
        setBookedIntervalToEmployeeAgenda(employee, cleaningServiceDto.getCleaningDate());
        createCleaningService(employee, cleaningServiceDto, userId);
    }

    public List<CleaningServiceDisplay> getCleaningServices(){
        List<CleaningServiceDisplayVO> cleaningServicesVO = repo.getCleaningServices();
        return cleaningServicesVO.stream()
                .map(mapper::toCleaningServiceDisplay)
                .collect(Collectors.toList());
    }

    public CleaningServiceDto getCleaningService(Long id){
        CleaningService cleaningService = repo.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.toCleaningServiceDto(cleaningService);
    }

    public void endCleaningService(Long id){
        CleaningService cleaningService = repo.findById(id).orElseThrow(EntityNotFoundException::new);
        cleaningService.setStatus(CleaningStatus.Deleted);
        Employee employee = cleaningService.getEmployee();
        deleteCleaningServiceFromAgenda(employee, cleaningService);
        employeeRepository.save(employee);
    }

    public void finishCleaningService(Long id, String date) {
        CleaningService cleaningService = repo.findById(id).orElseThrow(EntityNotFoundException::new);
        addDateOfCleaning(cleaningService, date);
        cleaningService.finishCleaning();
        repo.save(cleaningService);
    }

    public List<CleaningDateDto> getDatesOfCleaningForCleaningService(Long id){
        return repo.getDatesOfCleaningForCleaningService(id).stream()
                .filter(Objects::nonNull)
                .map(mapper::toCleaningDateDto)
                .collect(Collectors.toList());
    }

    public void addMessageToCleaningService(Long id, MessageDto dto){
        CleaningService cleaningService = repo.findById(id).orElseThrow(EntityNotFoundException::new);
        cleaningService.addMessage(mapper.toCleaningMessageEntity(dto));
        repo.save(cleaningService);
    }

    public List<MessageDto> getMessagesForCleaningService(Long id){
        return repo.getMessagesForCleaningService(id).stream()
                .filter(Objects::nonNull)
                .map(mapper::toMessageDto)
                .collect(Collectors.toList());
    }

    public ObjectValueDto<String> getNextCleaningDate(Long id){
        CleaningService cleaningService = repo.findById(id).orElseThrow(EntityNotFoundException::new);
        return new ObjectValueDto<>(cleaningService.getNextCleaningDate());
    }

    public CleaningServiceDescriptionDto getDescriptions(){
        List<CleaningServiceDescription> descriptions = (List<CleaningServiceDescription>) cleaningServiceDescriptionRepository.findAll();
        CleaningServiceDescription entity = descriptions.stream()
                .findFirst()
                .orElse(new CleaningServiceDescription());
        return cleaningServiceDescriptionMapper.toCleaningServiceDescriptionDto(entity);
    }

    public CleaningServicePricesDto getCleaningServicePrices(){
        List<CleaningServicePrices> descriptions = (List<CleaningServicePrices>) cleaningServicePricesRepository.findAll();
        CleaningServicePrices entity = descriptions.stream()
                .findFirst()
                .orElse(CleaningServicePrices.emptyInstance());
        return cleaningServicePricesMapper.toCleaningServicePricesDto(entity);
    }

    public List<DatesToRescheduleDto> getDatesToReschedule(Long id){
        CleaningService cleaningService = repo.findById(id).orElseThrow(EntityNotFoundException::new);
        return cleaningService.getDatesToReschedule();
    }

    public void rescheduleCleaningService(Long id, RescheduleDateDto dto){
        CleaningService cleaningService = repo.findById(id).orElseThrow(EntityNotFoundException::new);
        cleaningService.addRescheduledDate(mapper.toRescheduleDateEntity(dto));
        repo.save(cleaningService);
    }

    private void addDateOfCleaning(CleaningService cleaningService, String date){
        CleaningDate cleaningDate = cleaningService.getCleaningDate();
        cleaningService.addDateOfCleaning(new CleaningDate(date, cleaningDate.getStartingHour(), cleaningDate.getFinishingHour()));
    }

    private void deleteCleaningServiceFromAgenda(Employee employee, CleaningService cleaningService) {
        Day day = employee.getAgenda().getDays().stream()
                .filter(d -> d.getDate().equals(cleaningService.getCleaningDate().getCleaningDate()))
                .findFirst().get();

        BookedInterval bookedInterval = day.getBookedIntervals().stream()
                .filter(bI -> bI.getStartingHour() == cleaningService.getCleaningDate().getStartingHour())
                .findFirst().get();
        day.deleteBookedInterval(bookedInterval);
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
        cleaningService.setStatus(CleaningStatus.InProgress);
        employeeRepository.save(employee);
    }

    private void createCleaningServiceWithClient(Employee employee, Client client, CleaningServiceDto cleaningServiceDto){
        CleaningService cleaningService = mapper.toCleaningServiceEntity(cleaningServiceDto);
        client.addCleaningService(cleaningService);
        cleaningService.setClient(client);
        employee.addCleaningService(cleaningService);
        cleaningService.setEmployee(employee);
        cleaningService.setStatus(CleaningStatus.InProgress);
        userRepository.saveAll(List.of(client, employee));
    }
}
