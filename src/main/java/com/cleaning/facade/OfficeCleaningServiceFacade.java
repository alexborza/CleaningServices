package com.cleaning.facade;

import com.cleaning.facade.dto.OfficeCleaningDto;
import com.cleaning.facade.mapper.OfficeCleaningServiceMapper;
import com.cleaning.repository.OfficeCleaningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficeCleaningServiceFacade {

    @Autowired
    private OfficeCleaningRepository repository;

    @Autowired
    private OfficeCleaningServiceMapper mapper;

    public void quoteRequestForOfficeCleaning(OfficeCleaningDto officeCleaningDto){
        repository.save(mapper.toOfficeCleaningEntity(officeCleaningDto));
    }
}
