package com.cleaning.repository;

import com.cleaning.entity.*;
import com.cleaning.facade.vo.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CleaningServiceRepository extends CrudRepository<CleaningService, Long> {

    @Query("Select cs.id as id, cs.type as type, cs.contactInfo.phoneNumber as phoneNumber, " +
            "cs.contactInfo.email as email, cs.cleaningDetails.squareMeters as squareMeters, cs.status as status from CleaningService cs")
    List<CleaningServiceDisplayVO> getCleaningServices();

    @Query("Select cs from CleaningService cs where cs.client.id = ?1")
    List<CleaningService> getClientsCleaningServices(Long clientId);

    @Query("Select cs From CleaningService cs " +
            "where cs.cleaningDate.cleaningDate = ?1 " +
            "or (cs.cleaningFrequency = 'Weekly' and DATEDIFF(cs.cleaningDate.cleaningDate, ?1) < 0 and DATEDIFF(cs.cleaningDate.cleaningDate, ?1) % 7 = 0) " +
            "or (cs.cleaningFrequency = 'BiWeekly' and DATEDIFF(cs.cleaningDate.cleaningDate, ?1) < 0 and DATEDIFF(cs.cleaningDate.cleaningDate, ?1) % 14 = 0) " +
            "or (cs.cleaningFrequency = 'Monthly' and DATEDIFF(cs.cleaningDate.cleaningDate, ?1) < 0 and DATEDIFF(cs.cleaningDate.cleaningDate, ?1) % 28 = 0) ")
    List<CleaningService> getCleaningServicesForDate(String date);

    @Query("Select datesOfCleaning From CleaningService cs Left Join cs.datesOfCleaning datesOfCleaning where cs.id = ?1")
    List<CleaningDate> getDatesOfCleaningForCleaningService(Long id);

    @Query("Select messages From CleaningService cs Left Join cs.messages messages where cs.id = ?1")
    List<Message> getMessagesForCleaningService(Long id);
}
