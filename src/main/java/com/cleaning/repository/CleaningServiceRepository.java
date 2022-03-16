package com.cleaning.repository;

import com.cleaning.entity.CleaningService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CleaningServiceRepository extends CrudRepository<CleaningService, Long> {

    @Query("Select cs from CleaningService cs where cs.client.id = ?1")
    List<CleaningService> getClientsCleaningServices(Long clientId);

    @Query("Select cs From CleaningService cs " +
            "where cs.employee.id = ?1 " +
            "and cs.cleaningDate.cleaningDate = ?2 " +
            "or (cs.cleaningFrequency = 'Weekly' and DATEDIFF(cs.cleaningDate.cleaningDate, ?2) < 0 and DATEDIFF(cs.cleaningDate.cleaningDate, ?2) % 7 = 0) " +
            "or (cs.cleaningFrequency = 'BiWeekly' and DATEDIFF(cs.cleaningDate.cleaningDate, ?2) < 0 and DATEDIFF(cs.cleaningDate.cleaningDate, ?2) % 14 = 0) " +
            "or (cs.cleaningFrequency = 'Monthly' and DATEDIFF(cs.cleaningDate.cleaningDate, ?2) < 0 and DATEDIFF(cs.cleaningDate.cleaningDate, ?2) % 28 = 0) ")
    List<CleaningService> getEmployeeCleaningServicesForDate(Long id, String date);
}
