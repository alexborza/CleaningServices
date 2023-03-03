package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.domain.appointment.*;
import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.users.*;
import com.cleaning.exposition.representation.data.*;
import com.cleaning.exposition.representation.request.*;
import com.cleaning.exposition.representation.response.appointment.*;
import com.cleaning.exposition.representation.response.cleaning_service.*;
import com.cleaning.exposition.representation.response.cleaning_service.description.*;
import com.cleaning.exposition.representation.response.cleaning_service.prices.*;
import com.cleaning.exposition.representation.response.users.*;
import com.cleaning.infrastructure.appointment.data.*;
import com.cleaning.infrastructure.cleaning_service.data.*;
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
        EmployeeContractCreation employeeContractCreation = EmployeeContractCreationTestData.dummyEmployeeContractCreation("username", "email");

        when(encoder.encode(employeeContractCreation.getPassword())).thenReturn("encodedPass");

        ResponseEntity<Void> employeeContract = controller.createEmployeeContract(employeeContractCreation);

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
        String date = LocalDate.of(2023, 2, 8).toString();

        Employee e1 = UserTestData.dummyEmployeeWithId(1L, "alex", "alex");
        Employee e2 = UserTestData.dummyEmployeeWithId(2L, "alex", "alex");
        Employee e3 = UserTestData.dummyEmployeeWithId(3L, "alex", "alex");


        UserMinimalView v1 = createUserMinimalView(1L, "alex");
        UserMinimalView v2 = createUserMinimalView(2L, "patrick");
        UserMinimalView v3 = createUserMinimalView(3L, "cristi");

        CleaningService cleaningService = CleaningServiceTestData.dummyCleaningService(null);

        Appointment e1_1 = AppointmentTestData.dummyAppointment(cleaningService, e1, new TimeSlot(8, 10), LocalDate.parse(date), AppointmentStatus.ACTIVE);
        Appointment e1_2 = AppointmentTestData.dummyAppointment(cleaningService, e1, new TimeSlot(10, 12), LocalDate.parse(date), AppointmentStatus.ACTIVE);
        Appointment e1_3 = AppointmentTestData.dummyAppointment(cleaningService, e1, new TimeSlot(13, 17), LocalDate.parse(date), AppointmentStatus.ACTIVE);
        Appointment e2_1 = AppointmentTestData.dummyAppointment(cleaningService, e2, new TimeSlot(8, 14), LocalDate.parse(date), AppointmentStatus.ACTIVE);
        Appointment e2_2 = AppointmentTestData.dummyAppointment(cleaningService, e2, new TimeSlot(14, 17), LocalDate.parse(date), AppointmentStatus.ACTIVE);

        Map<UserMinimalView, List<Appointment>> map = new LinkedHashMap<>();
        map.put(v1, List.of(e1_1, e1_2, e1_3));
        map.put(v2, List.of(e2_1, e2_2));
        map.put(v3, Collections.emptyList());

        when(administratorService.getAllEmployeesAppointmentsByDate(date)).thenReturn(map);

        ResponseEntity<List<EmployeeAppointmentRepresentation>> appointmentsByDate = controller.getAllEmployeesAppointmentsByDate(date);

        assertThat(appointmentsByDate).isNotNull();
        assertThat(appointmentsByDate.getStatusCodeValue()).isEqualTo(200);

        List<EmployeeAppointmentRepresentation> body = appointmentsByDate.getBody();

        assertThat(body).isNotNull();
        assertThat(body).hasSize(3);

        EmployeeAppointmentRepresentation r1 = body.get(0);
        EmployeeAppointmentRepresentation r2 = body.get(1);
        EmployeeAppointmentRepresentation r3 = body.get(2);

        assertThat(body.stream().map(EmployeeAppointmentRepresentation::getEmployeeId).collect(toList())).containsExactly(1L, 2L, 3L);
        assertThat(r1.getEmployeeId()).isEqualTo(1L);
        assertThat(r2.getEmployeeId()).isEqualTo(2L);
        assertThat(r3.getEmployeeId()).isEqualTo(3L);
        assertThat(r1.getAppointmentRepresentations()).hasSize(3);
        assertThat(r2.getAppointmentRepresentations()).hasSize(2);
        assertThat(r3.getAppointmentRepresentations()).hasSize(0);
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

    @Test
    public void testFindAllCleaningServices() {
        List<CleaningServiceMinimalView> views = List.of(
                createCleaningServiceMinimalView(1L, 8, 10),
                createCleaningServiceMinimalView(2L, 10, 14),
                createCleaningServiceMinimalView(3L, 8, 12),
                createCleaningServiceMinimalView(4L, 14, 17)
        );

        when(administratorService.getAllCleaningServices()).thenReturn(views);

        ResponseEntity<List<CleaningServiceMinimalRepresentation>> response = controller.getAllCleaningServices();
        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);

        List<CleaningServiceMinimalRepresentation> body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.stream().map(CleaningServiceMinimalRepresentation::getId).collect(toList())).containsExactly(1L, 2L, 3L, 4L);
        assertThat(body.stream().map(CleaningServiceMinimalRepresentation::getTimeSlotRepresentation).map(TimeSlotRepresentation::getStartingHour).collect(toList()))
                .containsExactly(8, 10, 8, 14);
        assertThat(body.stream().map(CleaningServiceMinimalRepresentation::getTimeSlotRepresentation).map(TimeSlotRepresentation::getFinishingHour).collect(toList()))
                .containsExactly(10, 14, 12, 17);
    }

    private CleaningServiceMinimalView createCleaningServiceMinimalView(Long id, Integer startingHour, Integer endingHour) {
        return new CleaningServiceMinimalView() {
            @Override
            public Long getId() {
                return id;
            }

            @Override
            public CleaningType getCleaningType() {
                return CleaningType.STANDARD;
            }

            @Override
            public Double getTotal() {
                return 200.0;
            }

            @Override
            public Integer getTimeEstimation() {
                return 2;
            }

            @Override
            public LocalDate getNextCleaningDate() {
                return null;
            }

            @Override
            public Integer getStartingHour() {
                return startingHour;
            }

            @Override
            public Integer getEndingHour() {
                return endingHour;
            }
        };
    }

    private UserMinimalView createUserMinimalView(Long id, String fullName) {
        return new UserMinimalView() {
            @Override
            public Long getId() {
                return id;
            }

            @Override
            public String getFullName() {
                return fullName;
            }
        };
    }
}
