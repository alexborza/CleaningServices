package com.cleaning.infrastructure.appointment;

import com.cleaning.infrastructure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AppointmentProcessorSchedulerTest {

    @InjectMocks
    private AppointmentProcessorScheduler scheduler;

    @Mock
    private AppointmentProcessService appointmentProcessService;

    @Test
    public void testProcessNotCompletedAppointments() {
        scheduler.processNotCompletedAppointments();

        verify(appointmentProcessService).processNotCompletedAppointments();
    }
}
