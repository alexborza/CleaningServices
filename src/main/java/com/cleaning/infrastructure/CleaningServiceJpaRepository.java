package com.cleaning.infrastructure;

import com.cleaning.domain.cleaning_service.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface CleaningServiceJpaRepository extends JpaRepository<CleaningService, Long> {

    @Query("Select " +
            "cs.id as id, cs.cleaningType as cleaningType, cs.total as total, cs.timeEstimation as timeEstimation, " +
            "MIN(CASE WHEN ap.status = 'ACTIVE' THEN ap.cleaningDate ELSE NULL END) as nextCleaningDate, " +
            "MIN(CASE WHEN ap.status = 'ACTIVE' THEN ap.timeSlot.startingHour ELSE NULL END) as startingHour, " +
            "MIN(CASE WHEN ap.status = 'ACTIVE' THEN ap.timeSlot.endingHour ELSE NULL END) as endingHour " +
            "FROM CleaningService cs " +
            "inner join Appointment ap on ap.cleaningService.id = cs.id " +
            "where cs.client.id = ?1 " +
            "group by id, cleaningType, total, timeEstimation " +
            "order by nextCleaningDate")
    List<CleaningServiceMinimalView> findClientsCleaningServices(Long clientId);

    @Query("Select messages From CleaningService cs Left Join cs.messages messages where cs.id = ?1")
    List<Message> getMessagesForCleaningService(Long id);
}
