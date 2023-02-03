package com.cleaning.cleaning_service;

import com.cleaning.appointment.data.*;
import com.cleaning.cleaning_service.data.*;
import com.cleaning.domain.appointment.*;
import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.cleaning_service.details.*;
import com.cleaning.domain.users.*;
import com.cleaning.infrastructure.*;
import com.cleaning.users.data.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.junit.jupiter.*;
import org.springframework.transaction.annotation.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CleaningServiceRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CleaningServiceRepository cleaningServiceRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    @Transactional
    public void create_cleaning_service_with_user_and_appointments() {
        Client dummyClient = UserTestData.dummyClient();
        Employee dummyEmployee = UserTestData.dummyEmployee();
        Client client = clientRepository.saveAndFlush(dummyClient);
        Employee employee = employeeRepository.saveAndFlush(dummyEmployee);

        CleaningService dummyCleaningService = CleaningServiceTestData.dummyCleaningService(client);
        Appointment dummyAppointment = AppointmentTestData.dummyAppointment(dummyCleaningService, employee);

        CleaningService cleaningService = cleaningServiceRepository.saveAndFlush(dummyCleaningService);
        Appointment appointment = appointmentRepository.saveAndFlush(dummyAppointment);
        CleaningService appointmentCleaningService = appointment.getCleaningService();

        assertEquals(cleaningService.getId(), appointmentCleaningService.getId());
        assertEquals(cleaningService.getMessages().size(), 2);
        assertEquals(cleaningService.getClient().getUsername(), "clientUser");
        assertEquals(cleaningService.getClient().getEmail(), "clientEmail");
    }
}