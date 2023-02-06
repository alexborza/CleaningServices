package com.cleaning.infrastructure;

import com.cleaning.domain.cleaning_service.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface CleaningServiceJpaRepository extends JpaRepository<CleaningService, Long> {

//    @Query("Select cs.id as id, cs.type as type, cs.contactInfo.phoneNumber as phoneNumber, " +
//            "cs.contactInfo.email as email, cs.cleaningDetails.squareMeters as squareMeters, cs.status as status from CleaningService cs")
//    List<CleaningServiceDisplayVO> getCleaningServices();

    @Query("Select cs from CleaningService cs where cs.client.id = ?1")
    List<CleaningService> getClientsCleaningServices(Long clientId);

    @Query("Select messages From CleaningService cs Left Join cs.messages messages where cs.id = ?1")
    List<Message> getMessagesForCleaningService(Long id);
}
