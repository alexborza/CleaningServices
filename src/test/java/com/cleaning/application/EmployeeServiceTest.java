package com.cleaning.application;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.users.*;
import com.cleaning.infrastructure.appointment.data.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.time.*;
import java.util.*;

import static com.cleaning.infrastructure.users.data.UserTestData.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private JobInformationRepository jobInformationRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    public void shouldUpdateJobInformation() {
        Long id = 1L;
        JobInformation jobInformation = dummyJobInformation();
        when(jobInformationRepository.existsById(id)).thenReturn(true);

        employeeService.updateJobInformation(id, jobInformation);

        verify(jobInformationRepository).updateJobInformation(id, jobInformation);
    }

    @Test
    public void shouldThrowExceptionWhenUpdateJobInformation() {
        Long id = 1L;
        JobInformation jobInformation = dummyJobInformation();
        when(jobInformationRepository.existsById(id)).thenReturn(false);

        JobInformationNotFoundException exception = assertThrows(JobInformationNotFoundException.class,
                () -> employeeService.updateJobInformation(id, jobInformation));
        assertThat(exception.getMessage()).isEqualTo("JobInformation not found for id = " + id);
    }

    @Test
    public void testGetEmployeesAvailableIntervalsForDate() {
        String date = LocalDate.of(2023, 2, 17).toString();
        Employee e1 = dummyEmployeeWithId(1L, "e1", "e1_email");
        Employee e2 = dummyEmployeeWithId(2L, "e2", "e2_email");
        Employee e3 = dummyEmployeeWithId(3L, "e3", "e3_email");
        Employee e4 = dummyEmployeeWithId(4L, "e4", "e4_email");
        Employee e5 = dummyEmployeeWithId(5L, "e5", "e5_email");

        Appointment ap1 = AppointmentTestData.dummyAppointment(null, e1, new TimeSlot(8, 10), LocalDate.now(), AppointmentStatus.ACTIVE);
        Appointment ap2 = AppointmentTestData.dummyAppointment(null, e1, new TimeSlot(10, 12), LocalDate.now(), AppointmentStatus.ACTIVE);
        Appointment ap3 = AppointmentTestData.dummyAppointment(null, e2, new TimeSlot(8, 11), LocalDate.now(), AppointmentStatus.ACTIVE);
        Appointment ap4 = AppointmentTestData.dummyAppointment(null, e2, new TimeSlot(15, 17), LocalDate.now(), AppointmentStatus.ACTIVE);
        Appointment ap5 = AppointmentTestData.dummyAppointment(null, e3, new TimeSlot(8, 12), LocalDate.now(), AppointmentStatus.ACTIVE);
        Appointment ap6 = AppointmentTestData.dummyAppointment(null, e3, new TimeSlot(13, 15), LocalDate.now(), AppointmentStatus.ACTIVE);
        Appointment ap7 = AppointmentTestData.dummyAppointment(null, e4, new TimeSlot(8, 17), LocalDate.now(), AppointmentStatus.ACTIVE);
        Appointment ap8 = AppointmentTestData.dummyAppointment(null, e5, new TimeSlot(14, 17), LocalDate.now(), AppointmentStatus.ACTIVE);

        when(appointmentRepository.findAllByCleaningDate(date)).thenReturn(
                List.of(ap1, ap2, ap3, ap4, ap5, ap6, ap7, ap8)
        );
        when(userRepository.findAllEmployeeIds()).thenReturn(List.of(1L, 2L, 3L, 4L, 5L, 6L));

        Map<Long, Set<TimeSlot>> employeesAvailableIntervalsForDate = employeeService.getEmployeesAvailableIntervalsForDate(date, 2);
        assertThat(employeesAvailableIntervalsForDate).isNotNull();

        Set<TimeSlot> timeSlotsEmployee1 = employeesAvailableIntervalsForDate.get(1L);
        assertThat(timeSlotsEmployee1).containsExactly(new TimeSlot(13, 15), new TimeSlot(15, 17));

        Set<TimeSlot> timeSlotsEmployee2 = employeesAvailableIntervalsForDate.get(2L);
        assertThat(timeSlotsEmployee2).containsExactly(new TimeSlot(11, 14), new TimeSlot(13, 15));

        Set<TimeSlot> timeSlotsEmployee3 = employeesAvailableIntervalsForDate.get(3L);
        assertThat(timeSlotsEmployee3).containsExactly(new TimeSlot(15, 17));

        Set<TimeSlot> timeSlotsEmployee4 = employeesAvailableIntervalsForDate.get(4L);
        assertThat(timeSlotsEmployee4).isEmpty();

        Set<TimeSlot> timeSlotsEmployee5 = employeesAvailableIntervalsForDate.get(5L);
        assertThat(timeSlotsEmployee5).containsExactly(new TimeSlot(8, 10), new TimeSlot(11, 14));

        Set<TimeSlot> timeSlotsEmployee6 = employeesAvailableIntervalsForDate.get(6L);
        assertThat(timeSlotsEmployee6).containsExactly(new TimeSlot(8, 10), new TimeSlot(15, 17));
    }

    @Test
    public void testGetEmployeesAppointmentsForDate() {
        Long id = 1L;
        String date = "2023-02-20";
        employeeService.getEmployeeAppointmentsForDate(id, date);

        verify(appointmentRepository).findAllByEmployeeAndCleaningDate(id, date);
    }
}
