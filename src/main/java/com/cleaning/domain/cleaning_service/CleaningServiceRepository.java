package com.cleaning.domain.cleaning_service;

import java.util.*;

public interface CleaningServiceRepository {

    CleaningService save(CleaningService cleaningService);

    List<CleaningService> saveAll(Iterable<CleaningService> cleaningServices);

    Optional<CleaningService> findById(Long id);

    List<CleaningServiceMinimalView> findClientsCleaningServices(Long clientId);

    List<CleaningService> findAll();
}
