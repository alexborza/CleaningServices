package com.cleaning.infrastructure.appointment.data;

import com.cleaning.domain.appointment.*;

import java.time.*;

public class AppointmentCommandTestData {

    public static AppointmentCommand dummyAppointmentCommand(Long id) {
        return new AppointmentCommand(
                id,
                1L,
                1L,
                LocalDate.now().toString(),
                new TimeSlot(8, 10),
                AppointmentStatus.ACTIVE.toString()
        );
    }
}
