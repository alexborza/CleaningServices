package com.cleaning.infrastructure.appointment;

import com.cleaning.domain.appointment.*;
import com.cleaning.infrastructure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.time.*;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AppointmentsProcessServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentProcessService appointmentProcessService;

    @Captor ArgumentCaptor<LocalDate> currentDate;

    @Test
    public void testProcessNotCompletedAppointments() {
        appointmentProcessService.processNotCompletedAppointments();

        verify(appointmentRepository).updateNotCompletedDueAppointments(currentDate.capture());
    }
}
