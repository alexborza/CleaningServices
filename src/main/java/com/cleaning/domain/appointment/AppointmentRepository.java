package com.cleaning.domain.appointment;

import java.util.*;

public interface AppointmentRepository {

    Optional<Appointment> findById(Long id);

    boolean existsById(Long id);

    List<Appointment> findAllByCleaningDate(String date);

    void saveAll(Iterable<Appointment> appointments);

    List<Appointment> findAllByEmployeeAndCleaningDate(Long employeeId, String date);

    List<Appointment> findAllByCleaningService(Long cleaningServiceId);

    void updateStatusCompleted(Long id);

    void deleteById(Long id);

}
