package com.cleaning.appointment.data;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.users.*;

import java.time.*;

public class AppointmentTestData {

    public static Appointment dummyAppointment(CleaningService cleaningService, Employee employee) {
        return new Appointment.AppointmentBuilder()
                .withCleaningService(cleaningService)
                .withEmployee(employee)
                .withCleaningDate(LocalDate.of(2023, 2, 19))
                .withTimeSlot(new TimeSlot(9, 11))
                .withStatus(AppointmentStatus.ACTIVE)
                .build();
    }
}
