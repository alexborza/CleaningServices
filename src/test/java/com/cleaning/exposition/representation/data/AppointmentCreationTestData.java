package com.cleaning.exposition.representation.data;

import com.cleaning.exposition.representation.request.appointment.*;
import com.cleaning.exposition.representation.response.appointment.*;

import java.time.*;

public class AppointmentCreationTestData {

    public static AppointmentCreation dummyAppointmentCreation() {

        return new AppointmentCreation(
                1L,
                LocalDate.now().toString(),
                new TimeSlotRepresentation(8, 10)
        );
    }

    public static AppointmentCreation dummyAppointmentCreation(TimeSlotRepresentation timeSlotRepresentation) {

        return new AppointmentCreation(
                1L,
                LocalDate.now().toString(),
                timeSlotRepresentation
        );
    }
}
