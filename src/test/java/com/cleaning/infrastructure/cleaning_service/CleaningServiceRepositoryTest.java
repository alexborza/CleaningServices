package com.cleaning.infrastructure.cleaning_service;

import com.cleaning.infrastructure.appointment.data.*;
import com.cleaning.infrastructure.cleaning_service.data.*;
import com.cleaning.domain.appointment.*;
import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.users.*;
import com.cleaning.infrastructure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.junit.jupiter.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CleaningServiceRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CleaningServiceRepository cleaningServiceRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    @Transactional
    public void create_cleaning_service_with_user_and_appointments() {

        Optional<User> optionalClient = userRepository.findById(10001L);
        Optional<User> optionalEmployee = userRepository.findById(10002L);

        Client client = null;
        Employee employee = null;

        if(optionalClient.isPresent()){
            client = (Client) optionalClient.get();
        }

        if(optionalEmployee.isPresent()){
            employee = (Employee) optionalEmployee.get();
        }

        CleaningService dummyCleaningService = CleaningServiceTestData.dummyCleaningService(client);
        Appointment dummyAppointment = AppointmentTestData.dummyAppointment(dummyCleaningService, employee);

        CleaningService cleaningService = cleaningServiceRepository.saveAndFlush(dummyCleaningService);
        Appointment appointment = appointmentRepository.saveAndFlush(dummyAppointment);
        CleaningService appointmentCleaningService = appointment.getCleaningService();

        assertEquals(cleaningService.getId(), appointmentCleaningService.getId());
        assertEquals(cleaningService.getMessages().size(), 2);
        assertEquals(cleaningService.getClient().getUsername(), "clientUsername");
        assertEquals(cleaningService.getClient().getEmail(), "clientEmail");
    }

//    @Test
//    public void cleaning_services_find_all() {
//        List<CleaningService> all = cleaningServiceRepository.findAll();
//        System.out.println(all);
//    }
}
