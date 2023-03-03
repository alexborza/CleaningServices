package com.cleaning.exposition.representation.data;

import com.cleaning.domain.cleaning_service.*;
import com.cleaning.exposition.representation.request.appointment.*;
import com.cleaning.exposition.representation.request.cleaning_service.*;

import java.util.*;

public class CleaningServiceCreationTestData {

    public static CleaningServiceCreation dummyCleaningServiceCreation(
            List<AppointmentCreation> appointments
    ) {

        return new CleaningServiceCreation(
                null,
                ContactInfoCreationTestData.dummyContactInfoCreation(),
                LocationCreationTestData.dummyLocationCreation(),
                CleaningDetailsCreationTestData.dummyStandardCleaningDetailsCreation(),
                Frequency.ONE_TIME,
                Payment.CARD,
                200.0,
                2,
                CleaningType.STANDARD,
                appointments
        );
    }
}
