package com.cleaning.exposition.representation.data;

import com.cleaning.domain.cleaning_service.details.*;
import com.cleaning.exposition.representation.response.cleaning_service.details.*;

public class CleaningDetailsRepresentationTestData {

    public static CleaningDetailsRepresentation dummyStandardCleaningDetailsRepresentation() {

        return new StandardCleaningDetailsRepresentation(
                null,
                "squareMeters",
                Parking.FREE,
                HomeAccess.CALL,
                10,
                10,
                10
        );
    }
}
