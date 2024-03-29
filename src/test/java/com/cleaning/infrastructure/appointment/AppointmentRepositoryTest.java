package com.cleaning.infrastructure.appointment;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.appointment.data.*;
import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.users.*;
import com.cleaning.infrastructure.implementation.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.annotation.*;
import org.springframework.test.context.junit.jupiter.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.*;

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
    @DirtiesContext
    public void find_all_by_cleaning_date() {
        addAppointments();
        LocalDate cleaningDate = LocalDate.of(2023, 2, 8);
        List<Appointment> appointments = appointmentRepositoryImplementation.findAllByCleaningDate(cleaningDate.toString());

        assertThat(appointments.size()).isEqualTo(3);
        assertThat(appointments.stream().map(Appointment::getTimeSlot).collect(Collectors.toList()))
                .containsExactly(new TimeSlot(8, 10), new TimeSlot(10, 12), new TimeSlot(13, 15));
    }

    @Test
    @DirtiesContext
    public void find_all_by_employee_and_cleaning_date() {
        addAppointments();
        LocalDate cleaningDate = LocalDate.of(2023, 2, 8);
        List<Appointment> appointments = appointmentRepositoryImplementation.findAllByEmployeeAndCleaningDate(10002L, cleaningDate.toString());

        assertThat(appointments.size()).isEqualTo(3);
        assertThat(appointments.stream().map(Appointment::getTimeSlot).collect(Collectors.toList()))
                .containsExactly(new TimeSlot(8, 10), new TimeSlot(10, 12), new TimeSlot(13, 15));
    }

    @Test
    @DirtiesContext
    public void find_all_by_cleaning_service() {
        addAppointments();
        List<Appointment> appointments = appointmentRepositoryImplementation.findAllByCleaningService(10001L);

        //+1 from data.sql.
        assertThat(appointments.size()).isEqualTo(6);
        assertThat(appointments.stream().map(Appointment::getTimeSlot).collect(Collectors.toList()))
                .containsExactly(new TimeSlot(9, 11), new TimeSlot(8, 10), new TimeSlot(10, 12), new TimeSlot(13, 15), new TimeSlot(8, 12), new TimeSlot(8, 12));
    }

    @Test
    @DirtiesContext
    public void testUpdateStatusCompleted() {
        addAppointments();

        appointmentRepositoryImplementation.updateStatusCompleted(10006L);
        Optional<Appointment> optionalAppointment = appointmentRepositoryImplementation.findById(10006L);

        assertThat(optionalAppointment).isNotEmpty();
        Appointment appointment = optionalAppointment.get();
        assertThat(appointment.getStatus()).isEqualTo(AppointmentStatus.COMPLETED);
    }

    @Test
    @DirtiesContext
    public void testDeleteById() {
        appointmentRepositoryImplementation.deleteById(10001L);
        boolean exists = appointmentRepositoryImplementation.existsById(10001L);

        assertThat(exists).isFalse();
    }

    @Test
    @DirtiesContext
    public void testUpdateNotCompletedDueAppointments() {
        addAppointments();

        appointmentRepositoryImplementation.updateNotCompletedDueAppointments(LocalDate.now());
        List<Appointment> appointments = appointmentRepositoryImplementation.findAllByCleaningService(10001L);

        assertThat(appointments.stream().
                filter(ap -> ap.getCleaningDate().equals(LocalDate.of(2023, 2, 8)))
                .allMatch(ap -> ap.getStatus() == AppointmentStatus.COMPLETED)).isTrue();
        assertThat(appointments.stream().
                filter(ap -> ap.getCleaningDate().equals(LocalDate.of(2023, 2, 9)))
                .allMatch(ap -> ap.getStatus() == AppointmentStatus.COMPLETED)).isTrue();
        assertThat(appointments.stream().
                filter(ap -> ap.getCleaningDate().equals(LocalDate.now()))
                .allMatch(ap -> ap.getStatus() == AppointmentStatus.COMPLETED)).isFalse();
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
                cleaningService, employee, new TimeSlot(8,10), LocalDate.of(2023, 2, 8), AppointmentStatus.COMPLETED
        );

        Appointment ap2 = AppointmentTestData.dummyAppointment(
                cleaningService, employee, new TimeSlot(10,12), LocalDate.of(2023, 2, 8), AppointmentStatus.COMPLETED
        );

        Appointment ap3 = AppointmentTestData.dummyAppointment(
                cleaningService, employee, new TimeSlot(13,15), LocalDate.of(2023, 2, 8), AppointmentStatus.ACTIVE
        );

        Appointment ap4 = AppointmentTestData.dummyAppointment(
                cleaningService, employee, new TimeSlot(8,12), LocalDate.of(2023, 2, 9), AppointmentStatus.ACTIVE
        );

        Appointment ap5 = AppointmentTestData.dummyAppointment(
                cleaningService, employee, new TimeSlot(8,12), LocalDate.now(), AppointmentStatus.ACTIVE
        );

        List<Appointment> appointments = List.of(ap2, ap1, ap3, ap4, ap5);
        appointmentRepositoryImplementation.saveAll(appointments);
    }
}
