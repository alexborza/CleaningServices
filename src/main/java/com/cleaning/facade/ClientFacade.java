package com.cleaning.facade;

import com.cleaning.entity.*;
import com.cleaning.facade.dto.*;
import com.cleaning.facade.mapper.*;
import com.cleaning.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class ClientFacade {

    @Autowired
    private CleaningServiceRepository cleaningServiceRepository;

    @Autowired
    private CleaningServiceMapper cleaningServiceMapper;

    public List<CleaningServiceDto> getClientsCleaningServices(Long userId){
        List<CleaningService> cleaningServices = cleaningServiceRepository.getClientsCleaningServices(userId);
        return cleaningServices.stream()
                .map(cleaningServiceMapper::toCleaningServiceDto)
                .collect(Collectors.toList());
    }
}
