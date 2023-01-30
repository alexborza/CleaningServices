package com.cleaning.application;

import com.cleaning.domain.cleaning_service.*;
import com.cleaning.infrastructure.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class ClientService {

//    @Autowired
//    private CleaningServiceRepository cleaningServiceRepository;
//
//
//    public List<CleaningServiceDto> getClientsCleaningServices(Long userId){
//        List<CleaningService> cleaningServices = cleaningServiceRepository.getClientsCleaningServices(userId);
//        return cleaningServices.stream()
//                .map(cleaningServiceMapper::toCleaningServiceDto)
//                .collect(Collectors.toList());
//    }
}
