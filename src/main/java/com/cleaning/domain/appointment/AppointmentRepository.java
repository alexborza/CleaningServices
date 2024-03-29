package com.cleaning.domain.appointment;

import java.time.*;
import java.util.*;

public interface AppointmentRepository {

    Optional<Appointment> findById(Long id);

    boolean existsById(Long id);

    List<Appointment> findAllByCleaningDate(String date);

    Appointment save(Appointment appointment);

    void saveAll(Iterable<Appointment> appointments);

    List<Appointment> findAllByEmployeeAndCleaningDate(Long employeeId, String date);

    List<Appointment> findAllByCleaningService(Long cleaningServiceId);

    void updateStatusCompleted(Long id);

    void deleteById(Long id);

    void updateNotCompletedDueAppointments(LocalDate currentDate);

}
