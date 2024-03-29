package com.cleaning.infrastructure.implementation;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.appointment.data.*;
import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.cleaning_service.data.*;
import com.cleaning.domain.cleaning_service.exception.*;
import com.cleaning.domain.users.*;
import com.cleaning.exposition.representation.response.appointment.*;
import com.cleaning.exposition.representation.response.cleaning_service.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.test.annotation.*;
import org.springframework.transaction.annotation.*;

import java.time.*;
import java.util.*;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;

@Import({BCryptPasswordEncoder.class, CleaningServiceRepositoryImplementation.class})
@DataJpaTest
public class CleaningServiceRepositoryTest {

    @Autowired
    private CleaningServiceRepositoryImplementation cleaningServiceRepositoryImplementation;

    @Autowired
    private TestEntityManager entityManager;

    @MockBean
    private UserRepository userRepository;

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
                .withMessages(Collections.singletonList(new Message(LocalDateTime.now(), "newSender", "newContent")))
                .build();

        CleaningService save = cleaningServiceRepositoryImplementation.save(build);
        List<Message> messages = save.getMessages();
        assertThat(messages).hasSize(3);
    }

    private void populateData() {

        Client client = entityManager.find(Client.class, 10001L);
        Employee employee = entityManager.find(Employee.class, 10002L);

        CleaningService cs1 = CleaningServiceTestData.dummyCleaningService(client);
        CleaningService cs2 = CleaningServiceTestData.dummyCleaningService(client);
        CleaningService cs3 = CleaningServiceTestData.dummyCleaningService(null);

        entityManager.persist(cs1);
        entityManager.persist(cs2);
        entityManager.persist(cs3);
        entityManager.flush();

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

        entityManager.persist(ap1);
        entityManager.persist(ap2);
        entityManager.persist(ap3);
        entityManager.persist(ap4);
        entityManager.persist(ap5);
        entityManager.persist(ap6);
        entityManager.flush();
    }
}
