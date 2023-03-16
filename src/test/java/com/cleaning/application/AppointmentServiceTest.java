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
    public void testCancelAppointment() {
        when(appointmentRepository.existsById(1L)).thenReturn(true);

        appointmentService.cancelAppointment(1L);

        verify(appointmentRepository).deleteById(1L);
    }

    @Test
    public void testCancelAppointmentShouldThrowAppointmentNotFoundException() {
        when(appointmentRepository.existsById(1L)).thenReturn(false);

        AppointmentNotFoundException appointmentNotFoundException = assertThrows(AppointmentNotFoundException.class,
                () -> appointmentService.cancelAppointment(1L));

        assertThat(appointmentNotFoundException.getMessage()).isEqualTo("Appointment not found for id = " + 1L);
    }

    @Test
    public void testAddAppointment() {
        Employee employee = UserTestData.dummyEmployee("user", "email");
        Client client = UserTestData.dummyClient("clientuser", "clientemail");
        CleaningService cleaningService = CleaningServiceTestData.dummyCleaningService(client);

        AppointmentCommand appointmentCommand = AppointmentCommandTestData.dummyAppointmentCommand();

        Appointment appointment = appointmentCommand.toDomain(cleaningService, employee);

        when(userRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(cleaningServiceRepository.findById(1L)).thenReturn(Optional.of(cleaningService));

        appointmentService.addAppointment(1L, 1L, appointmentCommand);

        verify(appointmentRepository).save(appointment);
    }

    @Test
    public void testAddAppointmentShouldThrowUserNotFoundException() {

        AppointmentCommand appointmentCommand = AppointmentCommandTestData.dummyAppointmentCommand();

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        UserNotFoundException appointmentNotFoundException = assertThrows(UserNotFoundException.class,
                () -> appointmentService.addAppointment(1L, 1L, appointmentCommand));

        assertThat(appointmentNotFoundException.getMessage()).isEqualTo("User not found for id = " + 1L);
    }

    @Test
    public void testAddAppointmentShouldThrowCleaningServiceNotFoundException() {

        AppointmentCommand appointmentCommand = AppointmentCommandTestData.dummyAppointmentCommand();

        when(userRepository.findById(1L)).thenReturn(Optional.of(UserTestData.dummyEmployee("e", "e")));
        when(cleaningServiceRepository.findById(1L)).thenReturn(Optional.empty());

        CleaningServiceNotFoundException appointmentNotFoundException = assertThrows(CleaningServiceNotFoundException.class,
                () -> appointmentService.addAppointment(1L, 1L, appointmentCommand));

        assertThat(appointmentNotFoundException.getMessage()).isEqualTo("CleaningService not found for id = " + 1L);
    }
}
