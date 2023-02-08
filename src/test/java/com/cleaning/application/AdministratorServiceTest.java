package com.cleaning.application;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.cleaning_service.description.*;
import com.cleaning.domain.cleaning_service.prices.*;
import com.cleaning.domain.users.*;
import com.cleaning.exposition.representation.data.*;
import com.cleaning.exposition.representation.response.cleaning_service.description.*;
import com.cleaning.exposition.representation.response.cleaning_service.prices.*;
import com.cleaning.exposition.representation.response.users.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.security.crypto.password.*;

import java.time.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

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
    private PasswordEncoder encoder;

    @Test
    public void testCreateEmployeeContract() {
        UserRepresentation employee = UserRepresentationTestData.dummyEmployeeRepresentation(
                1L,
                UserRepresentationTestData.dummyEmployeeInformationRepresentation()
        );

        administratorService.createEmployeeContract(employee);

        verify(encoder).encode(any());
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
        CleaningDescriptionRepresentation representation = CleaningDescriptionRepresentationTestData.dummyCleaningDescriptionRepresentation();
        administratorService.createDescriptions(representation);

        verify(cleaningDescriptionRepository).save(any());
    }

    @Test
    public void testCreateCleaningServicePrices() {
        CleaningPricesRepresentation representation = CleaningPricesRepresentationTestData.dummyCleaningPricesRepresentation();
        administratorService.createCleaningPrices(representation);

        verify(cleaningPriceRepository).save(any());
    }
}
