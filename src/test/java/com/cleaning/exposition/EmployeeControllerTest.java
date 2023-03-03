package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.domain.appointment.*;
import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.users.*;
import com.cleaning.domain.users.job_information.*;
import com.cleaning.exposition.representation.data.*;
import com.cleaning.exposition.representation.request.*;
import com.cleaning.exposition.representation.response.appointment.*;
import com.cleaning.exposition.representation.response.shared.*;
import com.cleaning.infrastructure.cleaning_service.data.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;

import java.time.*;
import java.util.AbstractMap.*;
import java.util.*;
import java.util.stream.*;

import static com.cleaning.infrastructure.appointment.data.AppointmentTestData.*;
import static com.cleaning.infrastructure.users.data.UserTestData.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Test
    public void testUpdateJobInformation() {
        Long id = 1L;
        JobInformationCreation jobInformationCreation = EmployeeContractCreationTestData.dummyJobInformationCreation("workPhone", LocalDate.now(), 2000L);
        JobInformation jobInformation = jobInformationCreation.toDomain();

        ResponseEntity<Void> voidResponseEntity = employeeController.updateJobInformation(id, jobInformationCreation);
        assertThat(voidResponseEntity).isNotNull();
        assertThat(voidResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(employeeService).updateJobInformation(id, jobInformation);
    }

    @Test
    public void testGetEmployeesAppointmentsForDate() {
        Long id = 1L;
        String date = LocalDate.now().toString();
        Employee e1 = dummyEmployeeWithId(id, "e1", "e1_email");
        CleaningService cleaningService = CleaningServiceTestData.dummyCleaningService(null);

        List<Appointment> appointments = List.of(
                dummyAppointment(cleaningService, e1, new TimeSlot(8, 10), LocalDate.now(), AppointmentStatus.ACTIVE),
                dummyAppointment(cleaningService, e1, new TimeSlot(10, 12), LocalDate.now(), AppointmentStatus.ACTIVE)
        );

        when(employeeService.getEmployeeAppointmentsForDate(id, date)).thenReturn(appointments);

        ResponseEntity<List<AppointmentRepresentation>> response = employeeController.getEmployeesAppointmentsForDate(id, date);
        assertThat(response).isNotNull();

        List<AppointmentRepresentation> body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.stream().map(AppointmentRepresentation::getTimeSlot).map(TimeSlotRepresentation::getStartingHour).collect(Collectors.toList()))
                .containsExactly(8, 10);
        assertThat(body.stream().map(AppointmentRepresentation::getTimeSlot).map(TimeSlotRepresentation::getFinishingHour).collect(Collectors.toList()))
                .containsExactly(10, 12);
    }

    @Test
    public void testGetEmployeesAvailableIntervalsForDate() {
        String date = LocalDate.now().toString();
        Integer timeEstimation = 2;
        Map<Long, Set<TimeSlot>> map = Map.ofEntries(
                new SimpleEntry<>(1L, Set.of(new TimeSlot(13, 15), new TimeSlot(15, 17))),
                new SimpleEntry<>(2L, Set.of(new TimeSlot(11, 14), new TimeSlot(13, 15))),
                new SimpleEntry<>(3L, Set.of(new TimeSlot(15, 17))),
                new SimpleEntry<>(4L, Collections.emptySet()),
                new SimpleEntry<>(5L, Set.of(new TimeSlot(8, 10), new TimeSlot(11, 14))),
                new SimpleEntry<>(6L, Set.of(new TimeSlot(8, 10), new TimeSlot(15, 17)))
        );

        when(employeeService.getEmployeesAvailableIntervalsForDate(date, timeEstimation)).thenReturn(map);

        ResponseEntity<Set<EmployeeAvailableInterval>> response = employeeController.getEmployeesAvailableIntervalsForDate(date, timeEstimation);
        assertThat(response).isNotNull();

        Set<EmployeeAvailableInterval> body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body).hasSize(4);
        assertThat(body.stream().map(EmployeeAvailableInterval::getAvailableInterval).map(TimeSlotRepresentation::getStartingHour).collect(Collectors.toList()))
                .containsExactly(8, 11, 13, 15);
        assertThat(body.stream().map(EmployeeAvailableInterval::getAvailableInterval).map(TimeSlotRepresentation::getFinishingHour).collect(Collectors.toList()))
                .containsExactlyInAnyOrder(10, 14, 15, 17);
    }
}
