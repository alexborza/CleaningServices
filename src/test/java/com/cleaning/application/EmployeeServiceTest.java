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
        Employee e2 = dummyEmployeeWithId(2L, "e2", "e2_email");
        Employee e3 = dummyEmployeeWithId(3L, "e3", "e3_email");
        Employee e4 = dummyEmployeeWithId(4L, "e4", "e4_email");

        Appointment ap1 = AppointmentTestData.dummyAppointment(null, e1, new TimeSlot(8, 10), LocalDate.now(), AppointmentStatus.ACTIVE);
        Appointment ap2 = AppointmentTestData.dummyAppointment(null, e1, new TimeSlot(10, 12), LocalDate.now(), AppointmentStatus.ACTIVE);
        Appointment ap3 = AppointmentTestData.dummyAppointment(null, e2, new TimeSlot(8, 11), LocalDate.now(), AppointmentStatus.ACTIVE);
        Appointment ap4 = AppointmentTestData.dummyAppointment(null, e2, new TimeSlot(15, 17), LocalDate.now(), AppointmentStatus.ACTIVE);
        Appointment ap5 = AppointmentTestData.dummyAppointment(null, e3, new TimeSlot(8, 12), LocalDate.now(), AppointmentStatus.ACTIVE);
        Appointment ap6 = AppointmentTestData.dummyAppointment(null, e3, new TimeSlot(13, 15), LocalDate.now(), AppointmentStatus.ACTIVE);
        Appointment ap7 = AppointmentTestData.dummyAppointment(null, e4, new TimeSlot(8, 17), LocalDate.now(), AppointmentStatus.ACTIVE);

        when(appointmentRepository.findAllByCleaningDate(date)).thenReturn(
                List.of(ap1, ap2, ap3, ap4, ap5, ap6, ap7)
        );
        when(userRepository.findAllEmployeeIds()).thenReturn(List.of(1L, 2L, 3L, 4L, 5L));

        Map<Long, Set<TimeSlot>> employeesAvailableIntervalsForDate = employeeService.getEmployeesAvailableIntervalsForDate(date, 2);
        assertThat(employeesAvailableIntervalsForDate).isNotNull();
    }

//    @Test
//    public void testDistance() {
//        Location loc1 = new Location("Semlac strada Rozelor", 46.1089918, 20.9250075);
//        Location loc2 = new Location("Pecica Strada 1 numarul 63",    46.1711579, 21.0762079);
//        double distance = loc1.distanceTo(loc2) * 1.6;
//
//        double speed=65.0;
//        double time = distance/speed * 60;
//        System.out.println(time);
//    }
}

//class Location {
//    private final String name;
//    private final double longitude;
//    private final double latitude;
//
//    // create and initialize a point with given name and
//    // (latitude, longitude) specified in degrees
//    public Location(String name, double latitude, double longitude) {
//        this.name = name;
//        this.latitude = latitude;
//        this.longitude = longitude;
//    }
//
//    // return distance between this location and that location
//    // measured in statute miles
//    public double distanceTo(Location that) {
//        double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
//        double lat1 = Math.toRadians(this.latitude);
//        double lon1 = Math.toRadians(this.longitude);
//        double lat2 = Math.toRadians(that.latitude);
//        double lon2 = Math.toRadians(that.longitude);
//
//        // great circle distance in radians, using law of cosines formula
//        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
//                + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));
//
//        // each degree on a great circle of Earth is 60 nautical miles
//        double nauticalMiles = 60 * Math.toDegrees(angle);
//        return STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
//    }
//
//    // return string representation of this point
//    public String toString() {
//        return name + " (" + latitude + ", " + longitude + ")";
//    }
//}
