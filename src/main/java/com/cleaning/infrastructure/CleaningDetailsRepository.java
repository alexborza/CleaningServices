package com.cleaning.infrastructure;

import com.cleaning.domain.cleaning_service.details.CleaningDetails;
import org.springframework.data.repository.CrudRepository;

public interface CleaningDetailsRepository extends CrudRepository<CleaningDetails, Long> {
}
