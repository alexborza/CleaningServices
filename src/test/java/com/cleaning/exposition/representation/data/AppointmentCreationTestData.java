package com.cleaning.exposition.representation.data;

import com.cleaning.exposition.representation.request.*;
import com.cleaning.exposition.representation.response.appointment.*;

import java.time.*;

public class AppointmentCreationTestData {

    public static AppointmentCreation dummyAppointmentCreation() {

        return new AppointmentCreation(
                LocalDate.now().toString(),
                new TimeSlotRepresentation(8, 10)
        );
    }
}
