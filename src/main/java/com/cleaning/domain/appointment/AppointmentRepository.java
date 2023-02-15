package com.cleaning.domain.appointment;

import java.util.*;

public interface AppointmentRepository {

    List<Appointment> findAllByCleaningDate(String date);

    void saveAll(Iterable<Appointment> appointments);

    List<Appointment> findAllByEmployeeAndCleaningDate(Long employeeId, String date);

}
