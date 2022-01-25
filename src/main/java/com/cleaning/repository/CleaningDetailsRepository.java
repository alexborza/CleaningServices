package com.cleaning.repository;

import com.cleaning.entity.CleaningDetails;
import org.springframework.data.repository.CrudRepository;

public interface CleaningDetailsRepository extends CrudRepository<CleaningDetails, Long> {
}
