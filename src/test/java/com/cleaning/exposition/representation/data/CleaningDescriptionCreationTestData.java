package com.cleaning.exposition.representation.data;

import com.cleaning.exposition.representation.request.cleaning_service.description.*;

public class CleaningDescriptionCreationTestData {

    public static CleaningDescriptionCreation dummyCleaningCreationRepresentation() {
        return new CleaningDescriptionCreation(
                "standard",
                "deep",
                "postConstruction",
                "disinfection"
        );
    }
}
