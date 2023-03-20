package com.cleaning.application;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.cleaning_service.description.*;
import com.cleaning.domain.cleaning_service.prices.*;
import com.cleaning.domain.users.*;
import com.cleaning.domain.appointment.data.*;
import com.cleaning.infrastructure.cleaning_service.cleaning_description.data.*;
import com.cleaning.infrastructure.cleaning_service.cleaning_price.data.*;
import com.cleaning.domain.cleaning_service.data.*;
import com.cleaning.domain.users.data.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.time.*;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdministratorServiceTest {

    @InjectMocks
    private AdministratorService administratorService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private CleaningDescriptionRepository cleaningDescriptionRepository;

    @Mock
    private CleaningPriceRepository cleaningPriceRepository;

    @Mock
    private CleaningServiceRepository cleaningServiceRepository;

    @Test
    public void testCreateEmployeeContract() {
        Employee employee = UserTestData.dummyEmployee("dummyEmployeeUsername", "dummyEmployeeEmail");

        administratorService.createEmployeeContract(employee);

        verify(userRepository).save(any());
    }

    @Test
    public void testGetAllEmployees() {
        administratorService.getAllEmployees();

        verify(userRepository).findAllEmployeeMinimalViews();
    }

    @Test
    public void testGetAllEmployeesAppointmentsByDate() {
        String date = LocalDate.of(2023, 2, 8).toString();

        Employee e1 = UserTestData.dummyEmployeeWithId(1L, "alex", "alex");
        Employee e2 = UserTestData.dummyEmployeeWithId(2L, "alex", "alex");
        CleaningService cs = CleaningServiceTestData.dummyCleaningService(UserTestData.dummyClient("user", "pass"));

        List<UserMinimalView> minimalViews = List.of(
                createMinimalView(1L, "alex"),
                createMinimalView(2L, "patrick"),
                createMinimalView(3L, "cristi")
        );

        List<Appointment> appointments = List.of(
                AppointmentTestData.dummyAppointment(cs, e1, new TimeSlot(8, 10), LocalDate.parse(date), AppointmentStatus.ACTIVE),
                AppointmentTestData.dummyAppointment(cs, e1, new TimeSlot(10, 12), LocalDate.parse(date), AppointmentStatus.ACTIVE),
                AppointmentTestData.dummyAppointment(cs, e1, new TimeSlot(13, 17), LocalDate.parse(date), AppointmentStatus.ACTIVE),
                AppointmentTestData.dummyAppointment(cs, e2, new TimeSlot(8, 14), LocalDate.parse(date), AppointmentStatus.ACTIVE),
                AppointmentTestData.dummyAppointment(cs, e2, new TimeSlot(14, 17), LocalDate.parse(date), AppointmentStatus.ACTIVE)
        );

        when(userRepository.findAllEmployeeMinimalViews()).thenReturn(minimalViews);
        when(appointmentRepository.findAllByCleaningDate(date)).thenReturn(appointments);

        Map<UserMinimalView, List<Appointment>> allEmployeesAppointmentsByDate = administratorService.getAllEmployeesAppointmentsByDate(date);
        assertThat(allEmployeesAppointmentsByDate.size()).isEqualTo(3);
    }

    @Test
    public void testCreateCleaningServiceDescriptions() {
        CleaningDescription cleaningDescription = CleaningDescriptionTestData.dummyCleaningDescription();
        administratorService.createDescriptions(cleaningDescription);

        verify(cleaningDescriptionRepository).save(any());
    }

    @Test
    public void testCreateCleaningServicePrices() {
        CleaningPrice cleaningPrice = CleaningPriceTestData.dummyCleaningPrice();
        administratorService.createCleaningPrices(cleaningPrice);

        verify(cleaningPriceRepository).save(any());
    }

    @Test
    public void testGetAllCleaningServices() {
        administratorService.getAllCleaningServices();

        verify(cleaningServiceRepository).findAll();
    }

    private UserMinimalView createMinimalView(Long id, String fullName) {
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
