package com.cleaning.infrastructure.cleaning_service.data;

import com.cleaning.domain.cleaning_service.*;

public class CleaningServiceCommandTestData {

    public static CleaningServiceCommand dummyCleaningServiceCommand() {
        return new CleaningServiceCommand(
                null,
                ContactInfoTestData.dummyContactInfo(),
                LocationTestData.dummyLocation(),
                CleaningDetailsTestData.dummyStandardCleaningDetails(),
                Frequency.ONE_TIME,
                Payment.CARD,
                100.0,
                3,
                CleaningType.STANDARD
        );
    }
}
