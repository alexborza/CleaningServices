package com.cleaning.infrastructure.appointment.data;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.users.*;
import com.cleaning.infrastructure.cleaning_service.data.*;
import com.cleaning.infrastructure.users.data.*;

import java.time.*;

public class AppointmentTestData {

    public static Appointment dummyAppointment(CleaningService cleaningService, Employee employee, TimeSlot timeSlot, LocalDate localDate, AppointmentStatus status) {
        return new Appointment.AppointmentBuilder()
                .withCleaningService(cleaningService)
                .withEmployee(employee)
                .withCleaningDate(localDate)
                .withTimeSlot(timeSlot)
                .withStatus(status)
                .build();
    }

    public static Appointment dummyAppointment(TimeSlot timeSlot, LocalDate localDate, AppointmentStatus status) {
        return new Appointment.AppointmentBuilder()
                .withCleaningService(CleaningServiceTestData.dummyCleaningService(UserTestData.dummyClient("u1", "u1_email")))
                .withEmployee(UserTestData.dummyEmployee("e1", "e1_email"))
                .withCleaningDate(localDate)
                .withTimeSlot(timeSlot)
                .withStatus(status)
                .build();
    }
}
