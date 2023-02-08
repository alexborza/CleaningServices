package com.cleaning.infrastructure.implementation;

import com.cleaning.domain.cleaning_service.prices.*;
import com.cleaning.infrastructure.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Repository
public class CleaningPriceRepositoryImplementation implements CleaningPriceRepository {

    @Autowired
    private CleaningPriceJpaRepository jpaRepository;

    @Override
    public CleaningPrice save(CleaningPrice cleaningPrice) {
        return jpaRepository.save(cleaningPrice);
    }
}
