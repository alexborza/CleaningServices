package com.cleaning.infrastructure.cleaning_service.cleaning_description;

import com.cleaning.domain.cleaning_service.description.*;

public class CleaningDescriptionTestData {

    public static CleaningDescription dummyCleaningDescription() {
        return new CleaningDescription.CleaningDescriptionBuilder()
                .withStandardCleaningDescription("standard")
                .withDeepCleaningDescription("deep")
                .withPostConstructionCleaningDescription("postConstruction")
                .withDisinfectionCleaningDescription("disinfection")
                .build();
    }
}
