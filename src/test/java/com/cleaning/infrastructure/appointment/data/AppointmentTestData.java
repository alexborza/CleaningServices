package com.cleaning.infrastructure.appointment.data;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.users.*;

import java.time.*;

public class AppointmentTestData {

    public static Appointment dummyAppointment(CleaningService cleaningService, Employee employee, TimeSlot timeSlot, LocalDate localDate) {
        return new Appointment.AppointmentBuilder()
                .withCleaningService(cleaningService)
                .withEmployee(employee)
                .withCleaningDate(localDate)
                .withTimeSlot(timeSlot)
                .withStatus(AppointmentStatus.ACTIVE)
                .build();
    }
}
