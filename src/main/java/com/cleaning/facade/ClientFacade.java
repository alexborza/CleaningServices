package com.cleaning.facade;

import com.cleaning.entity.*;
import com.cleaning.facade.dto.*;
import com.cleaning.facade.dto.response.*;
import com.cleaning.facade.mapper.*;
import com.cleaning.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.*;

@Service
public class ClientFacade {

    @Autowired
    private CleaningServiceRepository cleaningServiceRepository;

    @Autowired
    private OfficeCleaningRepository officeCleaningRepository;

    @Autowired
    private CleaningServiceMapper cleaningServiceMapper;

    @Autowired
    private OfficeCleaningServiceMapper officeCleaningServiceMapper;

    public List<CleaningServiceDto> getClientsCleaningServices(Long userId){
        List<CleaningService> cleaningServices = cleaningServiceRepository.getClientsCleaningServices(userId);
        return cleaningServices.stream()
                .map(cleaningServiceMapper::toCleaningServiceDto)
                .collect(Collectors.toList());
    }

    public List<OfficeCleaningDto> getClientsOfficeCleanings(Long userId){
        List<OfficeCleaning> cleaningServices = officeCleaningRepository.getClientsOfficeCleanings(userId);
        return cleaningServices.stream()
                .map(officeCleaningServiceMapper::toOfficeCleaningDto)
                .collect(Collectors.toList());
    }
}
