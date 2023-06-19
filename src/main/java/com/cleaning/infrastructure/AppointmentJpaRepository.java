package com.cleaning.infrastructure;

import com.cleaning.domain.appointment.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.transaction.annotation.*;

import java.time.*;
import java.util.*;

public interface AppointmentJpaRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByCleaningDateOrderByTimeSlotStartingHourAsc(LocalDate date);

    @Query("Select ap from Appointment ap " +
            "where ap.employee.id = :employeeId " +
            "and ap.cleaningDate = :date " +
            "order by ap.timeSlot.startingHour ASC")
    List<Appointment> findAllByEmployeeAndCleaningDate(@Param("employeeId") Long employeeId, @Param("date") LocalDate date);

    @Query("Select ap  from Appointment ap " +
            "where ap.cleaningService.id = :cleaningServiceId " +
            "order by ap.cleaningDate, ap.timeSlot.startingHour")
    List<Appointment> findAllByCleaningService(@Param("cleaningServiceId") Long cleaningServiceId);

    @Transactional
    @Modifying
    @Query("UPDATE Appointment ap SET ap.status = 'COMPLETED' WHERE ap.id = :id")
    void updateStatusCompleted(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Appointment a SET a.status = 'COMPLETED' WHERE a.cleaningDate < :currentDate AND a.status <> 'COMPLETED'")
    void updateNotCompletedDueAppointments(@Param("currentDate") LocalDate currentDate);
}
