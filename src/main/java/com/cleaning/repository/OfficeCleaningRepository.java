package com.cleaning.repository;

import com.cleaning.entity.OfficeCleaning;
import org.springframework.data.repository.CrudRepository;

public interface OfficeCleaningRepository extends CrudRepository<OfficeCleaning, Long> {
}
