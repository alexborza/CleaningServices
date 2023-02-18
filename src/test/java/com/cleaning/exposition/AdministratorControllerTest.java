package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.domain.appointment.*;
import com.cleaning.domain.users.*;
import com.cleaning.exposition.representation.data.*;
import com.cleaning.exposition.representation.response.appointment.*;
import com.cleaning.exposition.representation.response.cleaning_service.description.*;
import com.cleaning.exposition.representation.response.cleaning_service.prices.*;
import com.cleaning.exposition.representation.response.users.*;
import com.cleaning.infrastructure.appointment.data.*;
import com.cleaning.infrastructure.users.data.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.*;

import java.time.*;
import java.util.*;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdministratorControllerTest {

    @InjectMocks
    private AdministratorController controller;

    @Mock
    private AdministratorService administratorService;

    @Mock
    private PasswordEncoder encoder;

    @Test
    public void testCreateEmployeeContract() {
        UserRepresentation representation = UserRepresentationTestData.dummyClientRepresentation(1L);

        when(encoder.encode(representation.getPassword())).thenReturn("encodedPass");

        ResponseEntity<Void> employeeContract = controller.createEmployeeContract(representation);

        assertThat(employeeContract).isNotNull();
        assertThat(employeeContract.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testGetAllEmployees() {

        Employee e1 = UserTestData.dummyEmployee("e1_user", "e1_email");
        Employee e2 = UserTestData.dummyEmployee("e2_user", "e2_email");

        List<User> employees = List.of(e1, e2);

        when(administratorService.getAllEmployees()).thenReturn(employees);

        ResponseEntity<List<UserRepresentation>> allEmployees = controller.getAllEmployees();

        assertThat(allEmployees).isNotNull();
        assertThat(allEmployees.getStatusCodeValue()).isEqualTo(200);

        List<UserRepresentation> body = allEmployees.getBody();

        assertThat(body).isNotNull();
        assertThat(body).hasSize(2);
        assertThat(body.stream().map(UserRepresentation::getUsername).collect(toList()))
                .containsExactly("e1_user", "e2_user");
    }

    @Test
    public void testGetAppointmentsByDate() {
        TimeSlot t1 = new TimeSlot(8, 11);
        TimeSlot t2 = new TimeSlot(13, 15);
        TimeSlot t3 = new TimeSlot(15, 17);

        Appointment a1 = AppointmentTestData.dummyAppointment(t1, LocalDate.now(), AppointmentStatus.ACTIVE);
        Appointment a2 = AppointmentTestData.dummyAppointment(t2, LocalDate.now(), AppointmentStatus.ACTIVE);
        Appointment a3 = AppointmentTestData.dummyAppointment(t3, LocalDate.now(), AppointmentStatus.ACTIVE);

        List<Appointment> appointments = List.of(a1, a2, a3);
        String date = LocalDate.of(2023, 2, 17).toString();

        when(administratorService.getAppointmentsByDate(date)).thenReturn(appointments);

        ResponseEntity<List<AppointmentRepresentation>> appointmentsByDate = controller.getAppointmentsByDate(date);

        assertThat(appointmentsByDate).isNotNull();
        assertThat(appointmentsByDate.getStatusCodeValue()).isEqualTo(200);

        List<AppointmentRepresentation> body = appointmentsByDate.getBody();

        assertThat(body).isNotNull();

        List<TimeSlotRepresentation> timeSlotRepresentations = body.stream()
                .map(AppointmentRepresentation::getTimeSlot)
                .collect(toList());

        assertThat(body).hasSize(3);
        assertThat(timeSlotRepresentations.stream().map(TimeSlotRepresentation::getStartingHour).collect(toList())).containsExactly(8, 13, 15);
        assertThat(timeSlotRepresentations.stream().map(TimeSlotRepresentation::getFinishingHour).collect(toList())).containsExactly(11, 15, 17);
    }

    @Test
    public void testCreateDescriptions() {
        CleaningDescriptionRepresentation representation = CleaningDescriptionRepresentationTestData.dummyCleaningDescriptionRepresentation();

        ResponseEntity<Void> response = controller.createDescriptions(representation);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testCreateCleaningPrices() {
        CleaningPricesRepresentation representation = CleaningPricesRepresentationTestData.dummyCleaningPricesRepresentation();

        ResponseEntity<Void> response = controller.createCleaningPrices(representation);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

}
