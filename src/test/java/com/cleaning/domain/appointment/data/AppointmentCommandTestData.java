package com.cleaning.domain.appointment.data;

import com.cleaning.domain.appointment.*;

import java.time.*;

public class AppointmentCommandTestData {

    public static AppointmentCommand dummyAppointmentCommand() {
        return new AppointmentCommand(
                1L,
                LocalDate.now().toString(),
                new TimeSlot(8, 10),
                AppointmentStatus.ACTIVE
        );
    }

    public static AppointmentCommand dummyAppointmentCommand(TimeSlot timeSlot) {
        return new AppointmentCommand(
                1L,
                LocalDate.now().toString(),
                timeSlot,
                AppointmentStatus.ACTIVE
        );
    }

    public static AppointmentCommand dummyAppointmentCommand(Long employeeId) {
        return new AppointmentCommand(
                employeeId,
                LocalDate.now().toString(),
                new TimeSlot(8, 10),
                AppointmentStatus.ACTIVE
        );
    }
}
