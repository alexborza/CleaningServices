package com.cleaning.exposition.representation.data;

import com.cleaning.domain.cleaning_service.details.*;
import com.cleaning.exposition.representation.request.cleaning_service.details.*;

public class CleaningDetailsCreationTestData {

    public static CleaningDetailsCreation dummyStandardCleaningDetailsCreation() {

        return new StandardCleaningDetailsCreation(
                "200.0",
                Parking.FREE,
                HomeAccess.CALL,
                10,
                10,
                10
        );
    }
}
