package com.cleaning.infrastructure.implementation;

import com.cleaning.domain.cleaning_service.*;
import com.cleaning.infrastructure.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public class CleaningServiceRepositoryImplementation implements CleaningServiceRepository {

    @Autowired
    private CleaningServiceJpaRepository jpaRepository;

    @Override
    public CleaningService save(CleaningService cleaningService) {
        return jpaRepository.save(cleaningService);
    }

    @Override
    public Optional<CleaningService> findById(Long id) {
        return jpaRepository.findById(id);
    }
}
