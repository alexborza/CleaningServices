package com.cleaning.domain.appointment;

import com.cleaning.domain.*;
import com.cleaning.infrastructure.appointment.data.*;
import com.cleaning.infrastructure.cleaning_service.data.*;
import com.cleaning.infrastructure.users.data.*;
import org.junit.jupiter.api.*;

import java.time.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppointmentTest {

    @Test
    void should_create_appointment() {
        Appointment appointment = AppointmentTestData.dummyAppointment(
                new TimeSlot(8, 10),
                LocalDate.now(),
                AppointmentStatus.ACTIVE
        );

        assertThat(appointment.getStatus()).isNotNull();
        assertThat(appointment.getTimeSlot()).isNotNull();
        assertThat(appointment.getCleaningDate()).isNotNull();
    }

    @Test
    void should_not_create_appointment_with_null_cleaning_service() {

        assertThrows(DomainConstraintViolationException.class,
                () -> AppointmentTestData.dummyAppointment(
                        null,
                        UserTestData.dummyEmployee("e1", "e1_email"),
                        new TimeSlot(8, 10),
                        LocalDate.now(),
                        AppointmentStatus.ACTIVE
                ));
    }

    @Test
    void should_not_create_appointment_with_null_employee() {

        assertThrows(DomainConstraintViolationException.class,
                () -> AppointmentTestData.dummyAppointment(
                        CleaningServiceTestData.dummyCleaningService(UserTestData.dummyClient("u1", "u1_email")),
                        null,
                        new TimeSlot(8, 10),
                        LocalDate.now(),
                        AppointmentStatus.ACTIVE
                ));
    }

    @Test
    void should_not_create_appointment_with_null_time_slot() {

        assertThrows(DomainConstraintViolationException.class,
                () -> AppointmentTestData.dummyAppointment(
                        CleaningServiceTestData.dummyCleaningService(UserTestData.dummyClient("u1", "u1_email")),
                        UserTestData.dummyEmployee("e1", "e1_email"),
                        null,
                        LocalDate.now(),
                        AppointmentStatus.ACTIVE
                ));
    }

    @Test
    void should_not_create_appointment_with_null_cleaning_date() {

        assertThrows(DomainConstraintViolationException.class,
                () -> AppointmentTestData.dummyAppointment(
                        CleaningServiceTestData.dummyCleaningService(UserTestData.dummyClient("u1", "u1_email")),
                        UserTestData.dummyEmployee("e1", "e1_email"),
                        new TimeSlot(8, 10),
                        null,
                        AppointmentStatus.ACTIVE
                ));
    }

    @Test
    void should_not_create_appointment_with_null_status() {

        assertThrows(DomainConstraintViolationException.class,
                () -> AppointmentTestData.dummyAppointment(
                        CleaningServiceTestData.dummyCleaningService(UserTestData.dummyClient("u1", "u1_email")),
                        UserTestData.dummyEmployee("e1", "e1_email"),
                        new TimeSlot(8, 10),
                        LocalDate.now(),
                        null
                ));
    }
}
