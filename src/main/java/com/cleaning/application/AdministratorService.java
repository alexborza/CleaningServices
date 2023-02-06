package com.cleaning.application;

import com.cleaning.domain.users.*;
import com.cleaning.exposition.representation.response.users.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class AdministratorService {

    @Autowired
    private Users users;
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @Autowired
//    private CleaningServiceRepository cleaningServiceRepository;
//
//    @Autowired
//    private CleaningServiceDescriptionRepository cleaningServiceDescriptionRepository;
//
//    @Autowired
//    private CleaningServicePricesRepository cleaningServicePricesRepository;
//
//    @Autowired
//    private PasswordEncoder encoder;

    public void createEmployeeContract(UserRepresentation representation){
        User user = representation.toDomain();
        users.save(user);
    }
//
//    public List<EmployeeDto> getAllEmployees(){
//        List<Employee> employees = employeeRepository.findAll();
//        return employees.stream()
//                .map(mapper::toEmployeeDto)
//                .collect(Collectors.toList());
//    }
//
//    public List<ServicesAgenda> getServicesAgenda(String date){
//
//        return null;
//    }
//
//    public void createDescriptions(CleaningServiceDescriptionDto dto){
//        cleaningServiceDescriptionRepository.save(cleaningServiceDescriptionMapper.toCleaningServiceDescriptionEntity(dto));
//    }
//
//    public void updateDescriptions(Long id, CleaningServiceDescriptionDto dto){
//
//    }
//
//    public void createCleaningPrices(CleaningServicePricesDto dto){
//        cleaningServicePricesRepository.save(cleaningServicePricesMapper.toCleaningServicePricesEntity(dto));
//    }
//
//    public void updateCleaningPrices(Long id, CleaningServicePricesDto dto) {
//
//    }
//
//    private void encodePassword(UserDto dto){
//        dto.setPassword(encoder.encode(dto.getPassword()));
//    }
}
