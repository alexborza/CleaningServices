package com.cleaning.exposition.representation.data;

import com.cleaning.exposition.representation.response.cleaning_service.description.*;

public class CleaningDescriptionRepresentationTestData {

    public static CleaningDescriptionRepresentation dummyCleaningDescriptionRepresentation() {
        return new CleaningDescriptionRepresentation(
                1L,
                "standard",
                "deep",
                "postConstruction",
                "disinfection"
        );
    }
}
