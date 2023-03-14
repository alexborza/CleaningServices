package com.cleaning.infrastructure.appointment.data;

import com.cleaning.domain.appointment.*;

import java.time.*;

public class AppointmentCommandTestData {

    public static AppointmentCommand dummyAppointmentCommand() {
        return new AppointmentCommand(
                LocalDate.now().toString(),
                new TimeSlot(8, 10),
                AppointmentStatus.ACTIVE
        );
    }

    public static AppointmentCommand dummyAppointmentCommand(TimeSlot timeSlot) {
        return new AppointmentCommand(
                LocalDate.now().toString(),
                timeSlot,
                AppointmentStatus.ACTIVE
        );
    }
}
