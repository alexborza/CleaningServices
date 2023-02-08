package com.cleaning.domain.cleaning_service;

import java.util.*;

public interface CleaningServiceRepository {

    CleaningService save(CleaningService cleaningService);

    Optional<CleaningService> findById(Long id);
}
