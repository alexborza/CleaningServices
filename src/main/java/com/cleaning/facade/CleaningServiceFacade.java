package com.cleaning.facade;

import com.cleaning.facade.dto.CleaningServiceDto;
import com.cleaning.facade.mapper.CleaningServiceMapper;
import com.cleaning.repository.CleaningServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CleaningServiceFacade {

    @Autowired
    private CleaningServiceRepository repo;

    @Autowired
    private CleaningServiceMapper mapper;

    public void createCleaningService(CleaningServiceDto cleaningServiceDto){
        repo.save(mapper.toCleaningServiceEntity(cleaningServiceDto));
    }

    public List<String> getBookedHoursForDate(String date){
        return repo.getBookedHoursForDate(date);
    }
}
