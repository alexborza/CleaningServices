package com.cleaning.facade;

import com.cleaning.entity.*;
import com.cleaning.facade.dto.*;
import com.cleaning.facade.dto.response.*;
import com.cleaning.facade.mapper.*;
import com.cleaning.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.*;

@Service
public class AdministratorFacade {

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CleaningServiceRepository cleaningServiceRepository;

    @Autowired
    private CleaningServiceDescriptionRepository cleaningServiceDescriptionRepository;

    @Autowired
    private CleaningServiceDescriptionMapper cleaningServiceDescriptionMapper;

    @Autowired
    private CleaningServicePricesRepository cleaningServicePricesRepository;

    @Autowired
    private CleaningServicePricesMapper cleaningServicePricesMapper;

    @Autowired
    private AdministratorMapper mapper;

    @Autowired
    private CleaningServiceMapper cleaningServiceMapper;

    @Autowired
    private PasswordEncoder encoder;

    public ResponseEntity<MessageResponse> createEmployeeContract(UserDto dto){
        if (userRepository.existsByUsername(dto.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Username is already taken!"));
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email is already in use!"));
        }
        encodePassword(dto);
        User employee = mapper.toUserEntity(dto);
        setEmployeeRole(employee);
        userRepository.save(employee);
        return ResponseEntity.ok(new MessageResponse("Employee registered successfully!"));
    }

    public List<EmployeeDto> getAllEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(mapper::toEmployeeDto)
                .collect(Collectors.toList());
    }

    public List<ServicesAgenda> getServicesAgenda(String date){
        List<ServicesAgenda> servicesAgenda = new ArrayList<>();
        List<CleaningService> cleaningServices = cleaningServiceRepository.getCleaningServicesForDate(date);
        cleaningServices = cleaningServices.stream()
                .distinct()
                .filter(cs -> filterDeletedCleaningServices(cs, date))
                .filter(cs -> filterRescheduledDates(cs, date))
                .collect(Collectors.toList());
        Map<String, List<CleaningService>> cleaningServiceMap = cleaningServices.stream()
                .collect(Collectors.groupingBy(CleaningService::getEmployeeName));
        for (Map.Entry<String,List<CleaningService>> entry : cleaningServiceMap.entrySet()){
            List<CleaningServiceDto> cleaningServicesDto = entry.getValue().stream()
                    .map(cleaningServiceMapper::toCleaningServiceDto)
                    .collect(Collectors.toList());
            servicesAgenda.add(new ServicesAgenda(entry.getKey(), cleaningServicesDto));
        }
        return servicesAgenda;
    }

    public void createDescriptions(CleaningServiceDescriptionDto dto){
        cleaningServiceDescriptionRepository.save(cleaningServiceDescriptionMapper.toCleaningServiceDescriptionEntity(dto));
    }

    public void updateDescriptions(Long id, CleaningServiceDescriptionDto dto){
        CleaningServiceDescription description = cleaningServiceDescriptionRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        updateDescriptions(description, dto);
        cleaningServiceDescriptionRepository.save(description);
    }

    public void createCleaningPrices(CleaningServicePricesDto dto){
        cleaningServicePricesRepository.save(cleaningServicePricesMapper.toCleaningServicePricesEntity(dto));
    }

    public void updateCleaningPrices(Long id, CleaningServicePricesDto dto) {
        CleaningServicePrices entity = cleaningServicePricesRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        CleaningServicePrices other = cleaningServicePricesMapper.toCleaningServicePricesEntity(dto);
        entity.mapCleaningServicePrices(other);
        cleaningServicePricesRepository.save(entity);
    }

    private void updateDescriptions(CleaningServiceDescription entity, CleaningServiceDescriptionDto dto){
        entity.setStandardCleaningDescription(dto.getStandardCleaningDescription());
        entity.setDeepCleaningDescription(dto.getDeepCleaningDescription());
        entity.setDisinfectionCleaningDescription(dto.getDisinfectionCleaningDescription());
        entity.setPostConstructionCleaningDescription(dto.getPostConstructionCleaningDescription());
    }

    private boolean filterDeletedCleaningServices(CleaningService cleaningService, String date){
        if(cleaningService.getStatus() == CleaningStatus.Deleted){
            List<CleaningDate> datesOfCleaning = cleaningService.getDatesOfCleaning();
            return datesOfCleaning.stream()
                    .anyMatch(fc -> fc.getCleaningDate().equals(date));
        }
        return true;
    }

    private boolean filterRescheduledDates(CleaningService cleaningService, String date) {
        return !cleaningService.isDateRescheduled(date);
    }

    private void encodePassword(UserDto dto){
        dto.setPassword(encoder.encode(dto.getPassword()));
    }

    private void setEmployeeRole(User employee){
        employee.setRoles(Set.of(this.getEmployeeRole(ERole.ROLE_EMPLOYEE)));
    }

    private Role getEmployeeRole(ERole role){
        return roleRepository.findByName(role)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    }
}
