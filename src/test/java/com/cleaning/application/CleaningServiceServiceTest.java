package com.cleaning.application;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.cleaning_service.description.*;
import com.cleaning.domain.cleaning_service.exception.*;
import com.cleaning.domain.cleaning_service.prices.*;
import com.cleaning.domain.users.*;
import com.cleaning.domain.users.exception.*;
import com.cleaning.infrastructure.appointment.data.*;
import com.cleaning.infrastructure.cleaning_service.cleaning_description.data.*;
import com.cleaning.infrastructure.cleaning_service.data.*;
import com.cleaning.infrastructure.users.data.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CleaningServiceServiceTest {

    @InjectMocks
    private CleaningServiceService cleaningServiceService;

    @Mock
    private CleaningServiceRepository cleaningServiceRepository;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CleaningDescriptionRepository cleaningDescriptionRepository;

    @Mock
    private CleaningPriceRepository cleaningPriceRepository;

    @Test
    public void testFindClientsCleaningServices() {

        cleaningServiceService.findClientsCleaningServices(1L);

        verify(cleaningServiceRepository).findClientsCleaningServices(1L);
    }

    @Test
    public void testGetCleaningService() {

        Client client = UserTestData.dummyClient("username", "email");
        CleaningService dummyCleaningService = CleaningServiceTestData.dummyCleaningService(client);

        when(cleaningServiceRepository.findById(1L)).thenReturn(Optional.ofNullable(dummyCleaningService));

        CleaningService cleaningService = cleaningServiceService.getCleaningService(1L);


        verify(cleaningServiceRepository).findById(1L);
        assert dummyCleaningService != null;
        assertThat(cleaningService.getCleaningType()).isEqualTo(dummyCleaningService.getCleaningType());
        assertThat(cleaningService.getTotal()).isEqualTo(dummyCleaningService.getTotal());
        assertThat(cleaningService.getPayment()).isEqualTo(dummyCleaningService.getPayment());
        assertThat(cleaningService.getClient().getUsername()).isEqualTo(dummyCleaningService.getClient().getUsername());
        assertThat(cleaningService.getClient().getEmail()).isEqualTo(dummyCleaningService.getClient().getEmail());
    }

    @Test
    public void testShouldThrowCleaningServiceNotFoundException() {

        when(cleaningServiceRepository.findById(1L)).thenReturn(Optional.empty());

        CleaningServiceNotFoundException exception = assertThrows(CleaningServiceNotFoundException.class,
                () -> cleaningServiceService.getCleaningService(1L));

        assertThat(exception.getMessage()).isEqualTo("CleaningService not found for id = " + 1L);

    }

    @Test
    public void testFindCleaningServiceAppointments() {

        List<Appointment> dummyAppointments = List.of(
                AppointmentTestData.dummyAppointment(new TimeSlot(8, 10), LocalDate.now(), AppointmentStatus.ACTIVE),
                AppointmentTestData.dummyAppointment(new TimeSlot(10, 12), LocalDate.now(), AppointmentStatus.ACTIVE)
        );

        when(appointmentRepository.findAllByCleaningService(1L)).thenReturn(dummyAppointments);

        List<Appointment> appointments = cleaningServiceService.findCleaningServiceAppointments(1L);

        verify(appointmentRepository).findAllByCleaningService(1L);
        assertThat(appointments).hasSize(2);
        assertThat(appointments.stream().map(Appointment::getTimeSlot).map(TimeSlot::getStartingHour).collect(Collectors.toList()))
                .containsExactly(8, 10);
        assertThat(appointments.stream().map(Appointment::getTimeSlot).map(TimeSlot::getEndingHour).collect(Collectors.toList()))
                .containsExactly(10, 12);
    }

    @Test
    public void testCreateCleaningService() {
        CleaningServiceCommand cleaningServiceCommand = CleaningServiceCommandTestData.dummyCleaningServiceCommand();
        List<AppointmentCommand> appointmentCommands = List.of(
                AppointmentCommandTestData.dummyAppointmentCommand(),
                AppointmentCommandTestData.dummyAppointmentCommand(),
                AppointmentCommandTestData.dummyAppointmentCommand()
        );

        Employee employee = UserTestData.dummyEmployee("Euser", "Eemail");
        Client client = UserTestData.dummyClient("Cuser", "Cemail");

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(employee));
        when(userRepository.findById(2L)).thenReturn(Optional.ofNullable(client));

        CleaningService cleaningService = cleaningServiceCommand.toDomain(client);

        List<Appointment> appointments = appointmentCommands.stream()
                .map(command -> command.toDomain(cleaningService, employee))
                .collect(Collectors.toList());

        cleaningServiceService.createCleaningService(1L, 2L, cleaningServiceCommand, appointmentCommands);

        verify(cleaningServiceRepository).save(cleaningService);
        verify(appointmentRepository).saveAll(appointments);
    }

    @Test
    public void testShouldThrowUserNotFoundExceptionForEmployeeWhenCreateCleaningService() {
        CleaningServiceCommand cleaningServiceCommand = CleaningServiceCommandTestData.dummyCleaningServiceCommand();
        List<AppointmentCommand> appointmentCommands = List.of(
                AppointmentCommandTestData.dummyAppointmentCommand(),
                AppointmentCommandTestData.dummyAppointmentCommand(),
                AppointmentCommandTestData.dummyAppointmentCommand()
        );
        Employee employee = UserTestData.dummyEmployee("Euser", "Eemail");

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(employee));
        when(userRepository.findById(2L)).thenReturn(Optional.empty());


        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> cleaningServiceService.createCleaningService(1L, 2L, cleaningServiceCommand, appointmentCommands));

        assertThat(exception.getMessage()).isEqualTo("User not found for id = " + 2L);
    }

    @Test
    public void testShouldThrowUserNotFoundExceptionForClientWhenCreateCleaningService() {
        CleaningServiceCommand cleaningServiceCommand = CleaningServiceCommandTestData.dummyCleaningServiceCommand();
        List<AppointmentCommand> appointmentCommands = List.of(
                AppointmentCommandTestData.dummyAppointmentCommand(),
                AppointmentCommandTestData.dummyAppointmentCommand(),
                AppointmentCommandTestData.dummyAppointmentCommand()
        );

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> cleaningServiceService.createCleaningService(1L, 2L, cleaningServiceCommand, appointmentCommands));

        assertThat(exception.getMessage()).isEqualTo("User not found for id = " + 1L);
    }

    @Test
    public void testAddMessageToCleaningService() {

        Client client = UserTestData.dummyClient("user", "email");
        when(cleaningServiceRepository.findById(1L)).thenReturn(Optional.of(CleaningServiceTestData.dummyCleaningService(client)));

        cleaningServiceService.addMessageToCleaningService(1L, CleaningServiceTestData.dummyMessage("message"));

        verify(cleaningServiceRepository).findById(1L);
        verify(cleaningServiceRepository).save(any());
    }

    @Test
    public void testGetDescriptions() {
        CleaningDescription dummyCleaningDescription = CleaningDescriptionTestData.dummyCleaningDescription();

        when(cleaningDescriptionRepository.findTopByOrderByIdDesc()).thenReturn(Optional.of(dummyCleaningDescription));

        Optional<CleaningDescription> cleaningDescription = cleaningServiceService.getDescriptions();

        verify(cleaningDescriptionRepository).findTopByOrderByIdDesc();
        assertThat(cleaningDescription).isNotEmpty();
        CleaningDescription cleaningDescription1 = cleaningDescription.get();
        assertThat(cleaningDescription1.getStandardCleaningDescription()).isEqualTo(dummyCleaningDescription.getStandardCleaningDescription());
        assertThat(cleaningDescription1.getDeepCleaningDescription()).isEqualTo(dummyCleaningDescription.getDeepCleaningDescription());
        assertThat(cleaningDescription1.getPostConstructionCleaningDescription()).isEqualTo(dummyCleaningDescription.getPostConstructionCleaningDescription());
        assertThat(cleaningDescription1.getDisinfectionCleaningDescription()).isEqualTo(dummyCleaningDescription.getDisinfectionCleaningDescription());
    }

    @Test
    public void testGetCleaningServicePrices() {
        cleaningServiceService.getCleaningServicePrices();

        verify(cleaningPriceRepository).findTopByOrderByIdDesc();
    }
}
