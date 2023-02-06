package com.cleaning.application;

import com.cleaning.domain.users.*;
import com.cleaning.exposition.mapper.*;
import com.cleaning.exposition.representation.response.*;
import com.cleaning.exposition.representation.response.users.*;
import com.cleaning.infrastructure.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class AdministratorService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;
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
        userRepository.save(user);
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
