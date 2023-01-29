package com.cleaning.infrastructure;

import com.cleaning.domain.cleaning_service.*;
import com.cleaning.application.vo.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CleaningServiceRepository extends CrudRepository<CleaningService, Long> {

    @Query("Select cs.id as id, cs.type as type, cs.contactInfo.phoneNumber as phoneNumber, " +
            "cs.contactInfo.email as email, cs.cleaningDetails.squareMeters as squareMeters, cs.status as status from CleaningService cs")
    List<CleaningServiceDisplayVO> getCleaningServices();

    @Query("Select cs from CleaningService cs where cs.client.id = ?1")
    List<CleaningService> getClientsCleaningServices(Long clientId);

    @Query("Select messages From CleaningService cs Left Join cs.messages messages where cs.id = ?1")
    List<Message> getMessagesForCleaningService(Long id);
}
