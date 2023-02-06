package com.cleaning.infrastructure.implementation;

import com.cleaning.domain.cleaning_service.*;
import com.cleaning.infrastructure.*;
import org.springframework.beans.factory.annotation.*;

public class CleaningServiceRepositoryImplementation implements CleaningServices {

    @Autowired
    private CleaningServiceJpaRepository jpaRepository;

    @Override
    public CleaningService save(CleaningService cleaningService) {
        return jpaRepository.save(cleaningService);
    }
}
