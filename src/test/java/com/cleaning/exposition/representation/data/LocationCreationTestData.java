package com.cleaning.exposition.representation.data;

import com.cleaning.exposition.representation.request.cleaning_service.*;

public class LocationCreationTestData {

    public static LocationCreation dummyLocationCreation() {

        return new LocationCreation(
                "county",
                "city",
                "address"
        );
    }
}
