package com.cleaning.exposition.representation.data;

import com.cleaning.exposition.representation.response.cleaning_service.*;

public class LocationRepresentationTestData {

    public static LocationRepresentation dummyLocationRepresentation() {

        return new LocationRepresentation(
                "county",
                "city",
                "address"
        );
    }
}
