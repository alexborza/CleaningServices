package com.cleaning.infrastructure.cleaning_service;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.users.*;
import com.cleaning.exposition.representation.response.appointment.*;
import com.cleaning.exposition.representation.response.cleaning_service.*;
import com.cleaning.infrastructure.appointment.data.*;
import com.cleaning.infrastructure.cleaning_service.data.*;
import com.cleaning.infrastructure.implementation.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.annotation.*;
import org.springframework.test.context.junit.jupiter.*;
import org.springframework.transaction.annotation.*;

import java.time.*;
import java.util.*;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CleaningServiceRepositoryTest {

    @Autowired
    private UserRepositoryImplementation userRepositoryImplementation;

    @Autowired
    private CleaningServiceRepositoryImplementation cleaningServiceRepositoryImplementation;

    @Autowired
    private AppointmentRepositoryImplementation appointmentRepositoryImplementation;

    @Test
    @DirtiesContext
    public void testFindClientsCleaningServices() {
        populateData();
        List<CleaningServiceMinimalView> serviceMinimalViews = cleaningServiceRepositoryImplementation.findClientsCleaningServices(10001L);

        List<CleaningServiceMinimalRepresentation> minimalRepresentations = serviceMinimalViews.stream()
                .map(CleaningServiceMinimalRepresentation::fromDomain)
                .collect(toList());

        assertThat(minimalRepresentations).hasSize(3);
        assertThat(minimalRepresentations.stream().map(CleaningServiceMinimalRepresentation::getId).collect(toList()))
                .containsExactly(10001L, 10002L, 10003L);
        assertThat(minimalRepresentations.stream().map(CleaningServiceMinimalRepresentation::getNextCleaningDate).collect(toList()))
                .containsExactly(null, LocalDate.of(2023, 3, 1), LocalDate.of(2023, 3, 2));
        assertThat(minimalRepresentations.stream().map(CleaningServiceMinimalRepresentation::getTimeSlotRepresentation).map(TimeSlotRepresentation::getStartingHour).collect(toList()))
                .containsExactly(null, 10, 8);
        assertThat(minimalRepresentations.stream().map(CleaningServiceMinimalRepresentation::getTimeSlotRepresentation).map(TimeSlotRepresentation::getFinishingHour).collect(toList()))
                .containsExactly(null, 12, 12);
    }

    @Test
    @DirtiesContext
    public void testFindAll() {
        populateData();
        List<CleaningServiceMinimalView> serviceMinimalViews = cleaningServiceRepositoryImplementation.findAll();

        List<CleaningServiceMinimalRepresentation> minimalRepresentations = serviceMinimalViews.stream()
                .map(CleaningServiceMinimalRepresentation::fromDomain)
                .collect(toList());

        assertThat(minimalRepresentations).hasSize(3);
        assertThat(minimalRepresentations.stream().map(CleaningServiceMinimalRepresentation::getId).collect(toList()))
                .containsExactly(10001L, 10002L, 10003L);
        assertThat(minimalRepresentations.stream().map(CleaningServiceMinimalRepresentation::getNextCleaningDate).collect(toList()))
                .containsExactly(null, LocalDate.of(2023, 3, 1), LocalDate.of(2023, 3, 2));
        assertThat(minimalRepresentations.stream().map(CleaningServiceMinimalRepresentation::getTimeSlotRepresentation).map(TimeSlotRepresentation::getStartingHour).collect(toList()))
                .containsExactly(null, 10, 8);
        assertThat(minimalRepresentations.stream().map(CleaningServiceMinimalRepresentation::getTimeSlotRepresentation).map(TimeSlotRepresentation::getFinishingHour).collect(toList()))
                .containsExactly(null, 12, 12);
    }

    @Test
    @Transactional
    @DirtiesContext
    public void updateCleaningServiceMessages() {
        CleaningService cleaningService = cleaningServiceRepositoryImplementation.findById(10001L)
                .orElseThrow(() -> new CleaningServiceNotFoundException(10001L));

        CleaningService build = new CleaningServiceBuilder()
                .withCleaningService(cleaningService)
                .withMessages(Collections.singletonList(new Message("newDate", "newSender", "newContent")))
                .build();

        CleaningService save = cleaningServiceRepositoryImplementation.save(build);
        List<Message> messages = save.getMessages();
        assertThat(messages).hasSize(3);
    }


    private void populateData() {
        Client client = (Client) userRepositoryImplementation.findById(10001L)
                .orElseThrow(() -> new UserNotFoundException(10001L));

        Employee employee = (Employee) userRepositoryImplementation.findById(10002L)
                .orElseThrow(() -> new UserNotFoundException(10001L));

        CleaningService cs1 = CleaningServiceTestData.dummyCleaningService(client);
        CleaningService cs2 = CleaningServiceTestData.dummyCleaningService(client);

        cleaningServiceRepositoryImplementation.saveAll(List.of(cs1, cs2));

        Appointment ap1 = AppointmentTestData.dummyAppointment(
                cs1, employee, new TimeSlot(8, 10), LocalDate.of(2023, 2, 21), AppointmentStatus.COMPLETED);

        Appointment ap2 = AppointmentTestData.dummyAppointment(
                cs1, employee, new TimeSlot(10, 12), LocalDate.of(2023, 3, 1), AppointmentStatus.ACTIVE);

        Appointment ap3 = AppointmentTestData.dummyAppointment(
                cs1, employee, new TimeSlot(15, 17), LocalDate.of(2023, 3, 8), AppointmentStatus.ACTIVE);

        Appointment ap4 = AppointmentTestData.dummyAppointment(
                cs2, employee, new TimeSlot(8, 10), LocalDate.of(2023, 2, 22), AppointmentStatus.COMPLETED);

        Appointment ap5 = AppointmentTestData.dummyAppointment(
                cs2, employee, new TimeSlot(8, 12), LocalDate.of(2023, 3, 2), AppointmentStatus.ACTIVE);

        Appointment ap6 = AppointmentTestData.dummyAppointment(
                cs2, employee, new TimeSlot(15, 17), LocalDate.of(2023, 3, 9), AppointmentStatus.ACTIVE);

        appointmentRepositoryImplementation.saveAll(
                List.of(ap1, ap2, ap3, ap4, ap5, ap6));
    }

}
