package com.cleaning.application;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.cleaning_service.description.*;
import com.cleaning.domain.cleaning_service.prices.*;
import com.cleaning.domain.users.*;
import com.cleaning.infrastructure.cleaning_service.cleaning_description.data.*;
import com.cleaning.infrastructure.cleaning_service.cleaning_price.data.*;
import com.cleaning.infrastructure.users.data.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.time.*;

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

    @Test
    public void testCreateEmployeeContract() {
        Employee employee = UserTestData.dummyEmployee("dummyEmployeeUsername", "dummyEmployeeEmail");

        administratorService.createEmployeeContract(employee);

        verify(userRepository).save(any());
    }

    @Test
    public void testGetAllEmployees() {
        administratorService.getAllEmployees();

        verify(userRepository).findAllByRole(Role.EMPLOYEE);
    }

    @Test
    public void testGetAppointmentsByDate() {
        String date = LocalDate.of(2023, 2, 8).toString();
        administratorService.getAppointmentsByDate(date);

        verify(appointmentRepository).findAllByCleaningDate(date);
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
}
