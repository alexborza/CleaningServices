package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.exposition.representation.data.*;
import com.cleaning.exposition.representation.response.appointment.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

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
    public void testCancelAppointment() {
        ResponseEntity<Void> response = appointmentController.cancelAppointment(1L);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(appointmentService).cancelAppointment(1L);
    }

    @Test
    public void testAddAppointment() {
        AppointmentCreation appointmentCreation = AppointmentCreationTestData.dummyAppointmentCreation();

        ResponseEntity<Void> response = appointmentController.addAppointment(1L, 1L, appointmentCreation);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(appointmentService).addAppointment(any(), any(), any());
    }
}
