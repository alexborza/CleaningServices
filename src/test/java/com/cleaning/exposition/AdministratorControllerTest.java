package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.exposition.representation.data.*;
import com.cleaning.exposition.representation.response.appointment.*;
import com.cleaning.exposition.representation.response.users.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

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
    public void testGetAllEmployees() {

        List<UserRepresentation> employees = List.of(
                UserRepresentationTestData.dummyEmployeeRepresentation(
                        1L,
                        UserRepresentationTestData.dummyEmployeeInformationRepresentation()
                ),
                UserRepresentationTestData.dummyEmployeeRepresentation(
                        2L,
                        UserRepresentationTestData.dummyEmployeeInformationRepresentation()
                )
        );

        when(administratorService.getAllEmployees()).thenReturn(employees);

        List<UserRepresentation> allEmployees = controller.getAllEmployees();

        assertThat(allEmployees).hasSize(2);
        assertThat(allEmployees.stream().map(UserRepresentation::getId).collect(Collectors.toList()))
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

        List<AppointmentRepresentation> appointmentsByDate = controller.getAppointmentsByDate(date);
        assertThat(appointmentsByDate).hasSize(2);
        assertThat(appointmentsByDate.stream().map(AppointmentRepresentation::getId).collect(Collectors.toList()))
                .containsExactly(1L, 2L);
    }

}
