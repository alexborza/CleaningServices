package com.cleaning.application;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.appointment.exception.*;
import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.cleaning_service.exception.*;
import com.cleaning.domain.users.*;
import com.cleaning.domain.users.exception.*;
import com.cleaning.domain.appointment.data.*;
import com.cleaning.domain.cleaning_service.data.*;
import com.cleaning.domain.users.data.*;
import com.cleaning.exposition.representation.data.*;
import com.cleaning.exposition.representation.request.appointment.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {

    @InjectMocks
    private AppointmentService appointmentService;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CleaningServiceRepository cleaningServiceRepository;

    @Test
    public void testCompleteAppointment() {
        when(appointmentRepository.existsById(1L)).thenReturn(true);

        appointmentService.completeAppointment(1L);

        verify(appointmentRepository).updateStatusCompleted(1L);
    }

    @Test
    public void testCompleteAppointmentShouldThrowAppointmentNotFoundException() {
        when(appointmentRepository.existsById(1L)).thenReturn(false);

        AppointmentNotFoundException appointmentNotFoundException = assertThrows(AppointmentNotFoundException.class,
                () -> appointmentService.completeAppointment(1L));

        assertThat(appointmentNotFoundException.getMessage()).isEqualTo("Appointment not found for id = " + 1L);
    }

    @Test
    public void testRescheduleAppointment() {
        Employee employee = UserTestData.dummyEmployee("user", "email");
        Client client = UserTestData.dummyClient("clientuser", "clientemail");
        CleaningService cleaningService = CleaningServiceTestData.dummyCleaningService(client);

        AppointmentCommand appointmentCommand = AppointmentCommandTestData.dummyAppointmentCommand();

        Appointment appointment = appointmentCommand.toDomain(cleaningService, employee);

        when(appointmentRepository.existsById(1L)).thenReturn(true);
        when(userRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(cleaningServiceRepository.findById(5L)).thenReturn(Optional.of(cleaningService));

        appointmentService.rescheduleAppointment(1L, 5L, appointmentCommand);

        verify(appointmentRepository).deleteById(1L);
        verify(appointmentRepository).save(appointment);
    }

    @Test
    public void testRescheduleAppointmentShouldThrowAppointmentNotFoundException() {
        AppointmentCreation appointmentCreation = AppointmentCreationTestData.dummyAppointmentCreation();

        when(appointmentRepository.existsById(1L)).thenReturn(false);

        AppointmentNotFoundException appointmentNotFoundException = assertThrows(AppointmentNotFoundException.class,
                () -> appointmentService.rescheduleAppointment(1L, 5L, appointmentCreation.toCommand()));

        assertThat(appointmentNotFoundException.getMessage()).isEqualTo("Appointment not found for id = " + 1L);
    }

    @Test
    public void testRescheduleAppointmentShouldThrowUserNotFoundException() {

        AppointmentCommand appointmentCommand = AppointmentCommandTestData.dummyAppointmentCommand();

        when(appointmentRepository.existsById(1L)).thenReturn(true);
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        UserNotFoundException appointmentNotFoundException = assertThrows(UserNotFoundException.class,
                () -> appointmentService.rescheduleAppointment(1L, 5L, appointmentCommand));

        assertThat(appointmentNotFoundException.getMessage()).isEqualTo("User not found for id = " + 1L);
    }

    @Test
    public void testRescheduleAppointmentShouldThrowCleaningServiceNotFoundException() {

        AppointmentCommand appointmentCommand = AppointmentCommandTestData.dummyAppointmentCommand();

        when(appointmentRepository.existsById(1L)).thenReturn(true);
        when(userRepository.findById(1L)).thenReturn(Optional.of(UserTestData.dummyEmployee("e", "e")));
        when(cleaningServiceRepository.findById(5L)).thenReturn(Optional.empty());

        CleaningServiceNotFoundException appointmentNotFoundException = assertThrows(CleaningServiceNotFoundException.class,
                () -> appointmentService.rescheduleAppointment(1L, 5L, appointmentCommand));

        assertThat(appointmentNotFoundException.getMessage()).isEqualTo("CleaningService not found for id = " + 5L);
    }
}
