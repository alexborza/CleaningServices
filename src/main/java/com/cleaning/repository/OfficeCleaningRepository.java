package com.cleaning.repository;

import com.cleaning.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;

import java.util.*;

public interface OfficeCleaningRepository extends CrudRepository<OfficeCleaning, Long> {
    @Query("Select oc from OfficeCleaning oc where oc.client.id = ?1")
    List<OfficeCleaning> getClientsOfficeCleanings(Long clientId);
}
