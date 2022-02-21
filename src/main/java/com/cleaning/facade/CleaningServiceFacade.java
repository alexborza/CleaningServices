package com.cleaning.facade;

import com.cleaning.entity.*;
import com.cleaning.facade.dto.CleaningServiceDto;
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
    private UserRepository userRepository;

    public void createCleaningService(Long userId, CleaningServiceDto cleaningServiceDto){
        if(userId == null) {
            repo.save(mapper.toCleaningServiceEntity(cleaningServiceDto));
        } else {
            Client client = userRepository.findClientById(userId)
                    .orElseThrow(EntityNotFoundException::new);
            CleaningService cleaningService = mapper.toCleaningServiceEntity(cleaningServiceDto);
            client.addCleaningService(cleaningService);
            cleaningService.setClient(client);
            userRepository.save(client);
        }
    }

    public List<String> getBookedHoursForDate(String date){
        return repo.getBookedHoursForDate(date);
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
}
