package com.cleaning.infrastructure.implementation;

import com.cleaning.domain.cleaning_service.description.*;
import com.cleaning.infrastructure.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public class CleaningDescriptionRepositoryImplementation implements CleaningDescriptionRepository {

    @Autowired
    private CleaningDescriptionJpaRepository jpaRepository;

    @Override
    public CleaningDescription save(CleaningDescription cleaningDescription) {
        return jpaRepository.save(cleaningDescription);
    }

    @Override
    public Optional<CleaningDescription> findTopByOrderByIdDesc() {
        return jpaRepository.findTopByOrderByIdDesc();
    }
}
