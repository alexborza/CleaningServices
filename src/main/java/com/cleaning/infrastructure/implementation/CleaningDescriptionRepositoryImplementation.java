package com.cleaning.infrastructure.implementation;

import com.cleaning.domain.cleaning_service.description.*;
import com.cleaning.infrastructure.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Repository
public class CleaningDescriptionRepositoryImplementation implements CleaningDescriptions {

    @Autowired
    private CleaningDescriptionJpaRepository jpaRepository;

    @Override
    public CleaningDescription save(CleaningDescription cleaningDescription) {
        return jpaRepository.save(cleaningDescription);
    }
}
