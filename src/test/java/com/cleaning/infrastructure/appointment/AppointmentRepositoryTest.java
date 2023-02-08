package com.cleaning.infrastructure.appointment;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.users.*;
import com.cleaning.infrastructure.appointment.data.*;
import com.cleaning.infrastructure.implementation.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.junit.jupiter.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepositoryImplementation appointmentRepositoryImplementation;

    @Autowired
    private UserRepositoryImplementation userRepositoryImplementation;

    @Autowired
    private CleaningServiceRepositoryImplementation cleaningServiceRepositoryImplementation;

    @Test
    public void find_all_by_cleaning_date() {
        addAppointments();
        LocalDate cleaningDate = LocalDate.of(2023, 2, 8);
        List<Appointment> appointments = appointmentRepositoryImplementation.findAllByCleaningDate(cleaningDate.toString());

        assertThat(appointments.size()).isEqualTo(3);
        assertThat(appointments.stream().map(Appointment::getTimeSlot).collect(Collectors.toList()))
                .containsExactlyInAnyOrder(new TimeSlot(9, 11), new TimeSlot(11, 13), new TimeSlot(13, 15));
    }

    private void addAppointments() {

        Optional<User> optionalEmployee = userRepositoryImplementation.findById(10002L);
        Optional<CleaningService> optionalCleaningService = cleaningServiceRepositoryImplementation.findById(10001L);

        Employee employee = null;
        CleaningService cleaningService = null;

        if(optionalEmployee.isPresent()){
            employee = (Employee) optionalEmployee.get();
        }

        if(optionalCleaningService.isPresent()){
            cleaningService = optionalCleaningService.get();
        }

        Appointment ap1 = AppointmentTestData.dummyAppointment(
                cleaningService, employee, new TimeSlot(9,11), LocalDate.of(2023, 2, 8)
        );

        Appointment ap2 = AppointmentTestData.dummyAppointment(
                cleaningService, employee, new TimeSlot(11,13), LocalDate.of(2023, 2, 8)
        );

        Appointment ap3 = AppointmentTestData.dummyAppointment(
                cleaningService, employee, new TimeSlot(13,15), LocalDate.of(2023, 2, 8)
        );

        Appointment ap4 = AppointmentTestData.dummyAppointment(
                cleaningService, employee, new TimeSlot(9,11), LocalDate.of(2023, 2, 9)
        );

        List<Appointment> appointments = List.of(ap1, ap2, ap3, ap4);
        appointmentRepositoryImplementation.saveAll(appointments);
    }
}
