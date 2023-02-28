package com.cleaning.exposition.representation.data;

import com.cleaning.domain.cleaning_service.*;
import com.cleaning.exposition.representation.response.appointment.*;
import com.cleaning.exposition.representation.response.cleaning_service.*;

import java.util.*;

public class CleaningServiceRepresentationTestData {

    public static CleaningServiceRepresentation dummyCleaningServiceRepresentation(
            List<MessageRepresentation> messages,
            List<AppointmentRepresentation> appointments
    ) {

        return new CleaningServiceRepresentation(
                null,
                ContactInfoRepresentationTestData.dummyContactInfoRepresentation(),
                LocationRepresentationTestData.dummyLocationRepresentation(),
                CleaningDetailsRepresentationTestData.dummyStandardCleaningDetailsRepresentation(),
                Frequency.ONE_TIME,
                Payment.CARD,
                200.0,
                2,
                CleaningType.STANDARD,
                messages,
                appointments
        );
    }
}
