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
import static org.assertj.core.api.Assertions.assertThat;
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
    public void testGetEmployeesAvailableIntervalsForDate() {
        String date = LocalDate.of(2023, 2, 17).toString();
        Employee e1 = dummyEmployeeWithId(1L, "e1", "e1_email");
        Employee e2 = dummyEmployeeWithId(2L, "e1", "e1_email");

        Appointment ap1 = AppointmentTestData.dummyAppointment(null, e1, new TimeSlot(8, 12), LocalDate.now(), AppointmentStatus.ACTIVE);
        Appointment ap2 = AppointmentTestData.dummyAppointment(null, e1, new TimeSlot(15, 17), LocalDate.now(), AppointmentStatus.ACTIVE);
        Appointment ap3 = AppointmentTestData.dummyAppointment(null, e2, new TimeSlot(8, 11), LocalDate.now(), AppointmentStatus.ACTIVE);

        when(appointmentRepository.findAllByCleaningDate(date)).thenReturn(
                List.of(ap1, ap2, ap3)
        );
        when(userRepository.findAllEmployeeIds()).thenReturn(List.of(1L, 2L, 3L));

        Map<Long, Set<TimeSlot>> employeesAvailableIntervalsForDate = employeeService.getEmployeesAvailableIntervalsForDate(date, 2);
        //bug pentru programarea pana la ora 12... in rest pare okay - solved
        //in solutia cu if-uri nested => cand timeEstimation > 4 nu se va genera niciun availableInterval indiferent de availableHours, cuz` of lunchBreak.
        //acum avem algoritmul functionabil, dar fara pauza de masa de 1h intre 12-13. Trebuie gandita o solutie.
        assertThat(employeesAvailableIntervalsForDate).isNotNull();
    }
}
