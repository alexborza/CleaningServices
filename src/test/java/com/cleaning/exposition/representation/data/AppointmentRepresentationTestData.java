package com.cleaning.exposition.representation.data;

import com.cleaning.exposition.representation.response.appointment.*;

import java.time.*;

public class AppointmentRepresentationTestData {

    public static AppointmentRepresentation dummyAppointmentRepresentation(Long id) {
        return new AppointmentRepresentation(
                id,
                1L,
                1L,
                LocalDate.of(2023, 2, 8).toString(),
                new TimeSlotRepresentation(9, 11),
                "ACTIVE"
        );
    }
}
