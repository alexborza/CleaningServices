package com.cleaning.repository;

import com.cleaning.entity.CleaningService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CleaningServiceRepository extends CrudRepository<CleaningService, Long> {

    @Query("Select cs.cleaningDate.cleaningHour from CleaningService cs Where cs.cleaningDate.cleaningDate = ?1")
    List<String> getBookedHoursForDate(String cleaningDate);
}
