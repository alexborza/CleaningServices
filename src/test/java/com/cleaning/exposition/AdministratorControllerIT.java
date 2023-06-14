package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.domain.appointment.*;
import com.cleaning.domain.appointment.data.*;
import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.cleaning_service.data.*;
import com.cleaning.domain.users.*;
import com.cleaning.domain.users.data.*;
import com.cleaning.exposition.representation.data.*;
import com.cleaning.exposition.representation.request.cleaning_service.description.*;
import com.cleaning.exposition.representation.request.cleaning_service.prices.*;
import com.cleaning.exposition.representation.request.users.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.result.*;

import java.time.*;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdministratorController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AdministratorControllerIT extends BaseControllerTestConfiguration {

    @MockBean
    private AdministratorService administratorService;

    @Test
    public void testCreateEmployeeContract() throws Exception {
        EmployeeContractCreation employeeContractCreation = EmployeeContractCreationTestData.dummyEmployeeContractCreation("username", "email");

        ResultActions response = mockMvc.perform(post("/api/administrator/employee-contract")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeContractCreation)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testGetAllEmployees() throws Exception {

        UserMinimalView e1 = createUserMinimalView(1L, "e1");
        UserMinimalView e2 = createUserMinimalView(1L, "e2");

        List<UserMinimalView> employees = List.of(e1, e2);

        when(administratorService.getAllEmployees()).thenReturn(employees);

        ResultActions response = mockMvc.perform(get("/api/administrator/employees")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.size()", is(employees.size())));

    }

    @Test
    public void testGetAppointmentsByDate() throws Exception {
        String date = LocalDate.of(2023, 2, 8).toString();

        Employee e1 = UserTestData.dummyEmployeeWithId(1L, "alex", "alex");
        Employee e2 = UserTestData.dummyEmployeeWithId(2L, "alex", "alex");

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

        ResultActions response = mockMvc.perform(get("/api/administrator/employees-appointments/{date}", date)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.size()", is(map.size())))
                .andExpect(jsonPath("$[*].employeeId", contains(1, 2, 3)))
                .andExpect(jsonPath("$[0].appointmentRepresentations", hasSize(3)))
                .andExpect(jsonPath("$[1].appointmentRepresentations", hasSize(2)))
                .andExpect(jsonPath("$[2].appointmentRepresentations", hasSize(0)));
        ;
    }

    @Test
    public void testCreateDescriptions() throws Exception {
        CleaningDescriptionCreation cleaningDescriptionCreation = CleaningDescriptionCreationTestData.dummyCleaningCreationRepresentation();

        ResultActions response = mockMvc.perform(post("/api/administrator/create-descriptions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cleaningDescriptionCreation)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testCreateCleaningPrices() throws Exception {
        CleaningPriceCreation cleaningPriceCreation = CleaningPricesCreationTestData.dummyCleaningPricesRepresentation();

        ResultActions response = mockMvc.perform(post("/api/administrator/create-prices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cleaningPriceCreation)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void testFindAllCleaningServices() throws Exception {
        List<CleaningServiceMinimalView> views = List.of(
                createCleaningServiceMinimalView(1L, 8, 10),
                createCleaningServiceMinimalView(2L, 10, 14),
                createCleaningServiceMinimalView(3L, 8, 12),
                createCleaningServiceMinimalView(4L, 14, 17)
        );

        when(administratorService.getAllCleaningServices()).thenReturn(views);

        ResultActions response = mockMvc.perform(get("/api/administrator/all-cleaning-services")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.size()", is(views.size())))
                .andExpect(jsonPath("$[*].id", contains(1, 2, 3, 4)))
                .andExpect(jsonPath("$[*].timeSlotRepresentation.startingHour", contains(8, 10, 8, 14)))
                .andExpect(jsonPath("$[*].timeSlotRepresentation.finishingHour", contains(10, 14, 12, 17)));
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
