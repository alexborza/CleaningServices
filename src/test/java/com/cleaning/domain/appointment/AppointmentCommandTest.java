package com.cleaning.domain.appointment;

import com.cleaning.domain.appointment.data.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppointmentCommandTest {

    @Test
    void should_appointment_commands_be_equal() {
        AppointmentCommand ap1 = AppointmentCommandTestData.dummyAppointmentCommand();
        AppointmentCommand ap2 = AppointmentCommandTestData.dummyAppointmentCommand();

        assertThat(ap1.equals(ap2)).isTrue();
    }
}
