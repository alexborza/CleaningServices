package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.exposition.representation.data.*;
import com.cleaning.exposition.representation.response.appointment.*;
import com.cleaning.exposition.representation.response.cleaning_service.description.*;
import com.cleaning.exposition.representation.response.cleaning_service.prices.*;
import com.cleaning.exposition.representation.response.users.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdministratorControllerTest {

    @InjectMocks
    private AdministratorController controller;

    @Mock
    private AdministratorService administratorService;

    @Test
    public void testCreateEmployeeContract() {
        UserRepresentation representation = UserRepresentationTestData.dummyClientRepresentation(1L);

        ResponseEntity<Void> employeeContract = controller.createEmployeeContract(representation);

        assertThat(employeeContract).isNotNull();
        assertThat(employeeContract.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testGetAllEmployees() {

        List<UserRepresentation> employees = List.of(
                UserRepresentationTestData.dummyEmployeeRepresentation(
                        1L,
                        UserRepresentationTestData.dummyJobInformationRepresentation()
                ),
                UserRepresentationTestData.dummyEmployeeRepresentation(
                        2L,
                        UserRepresentationTestData.dummyJobInformationRepresentation()
                )
        );

        when(administratorService.getAllEmployees()).thenReturn(employees);

        ResponseEntity<List<UserRepresentation>> allEmployees = controller.getAllEmployees();

        assertThat(allEmployees).isNotNull();
        assertThat(allEmployees.getStatusCodeValue()).isEqualTo(200);

        List<UserRepresentation> body = allEmployees.getBody();

        assertThat(body).isNotNull();
        assertThat(body).hasSize(2);
        assertThat(body.stream().map(UserRepresentation::getId).collect(Collectors.toList()))
                .containsExactly(1L, 2L);
    }

    @Test
    public void testGetAppointmentsByDate() {
        List<AppointmentRepresentation> appointmentRepresentations = List.of(
                AppointmentRepresentationTestData.dummyAppointmentRepresentation(1L),
                AppointmentRepresentationTestData.dummyAppointmentRepresentation(2L)
        );
        String date = LocalDate.of(2023, 2, 8).toString();

        when(administratorService.getAppointmentsByDate(date)).thenReturn(appointmentRepresentations);

        ResponseEntity<List<AppointmentRepresentation>> appointmentsByDate = controller.getAppointmentsByDate(date);

        assertThat(appointmentsByDate).isNotNull();
        assertThat(appointmentsByDate.getStatusCodeValue()).isEqualTo(200);

        List<AppointmentRepresentation> body = appointmentsByDate.getBody();

        assertThat(body).isNotNull();
        assertThat(body).hasSize(2);

        assertThat(body).hasSize(2);
        assertThat(body.stream().map(AppointmentRepresentation::getId).collect(Collectors.toList()))
                .containsExactly(1L, 2L);
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
