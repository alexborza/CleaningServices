package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.exposition.representation.data.*;
import com.cleaning.exposition.representation.request.appointment.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppointmentControllerTest {

    @InjectMocks
    private AppointmentController appointmentController;

    @Mock
    private AppointmentService appointmentService;

    @Test
    public void testCompleteAppointment() {
        ResponseEntity<Void> response = appointmentController.completeAppointment(1L);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(appointmentService).completeAppointment(1L);
    }

    @Test
    public void testRescheduleAppointment() {
        AppointmentCreation appointmentCreation = AppointmentCreationTestData.dummyAppointmentCreation();
        ResponseEntity<Void> response = appointmentController.rescheduleAppointment(1L, 1L, appointmentCreation);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(appointmentService).rescheduleAppointment(1L, 1L, appointmentCreation.toCommand());
    }
}
