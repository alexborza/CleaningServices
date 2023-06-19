package com.cleaning.infrastructure.cleaning_service.cleaning_description.data;

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

    public static CleaningDescription dummyCleaningDescription(String standard, String deep, String postContruction, String disinfection) {
        return new CleaningDescription.CleaningDescriptionBuilder()
                .withStandardCleaningDescription(standard)
                .withDeepCleaningDescription(deep)
                .withPostConstructionCleaningDescription(postContruction)
                .withDisinfectionCleaningDescription(disinfection)
                .build();
    }
}
