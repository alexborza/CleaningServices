package com.cleaning.exposition.representation.response.cleaning_service.description;

import com.cleaning.domain.cleaning_service.description.*;
import lombok.*;


@Getter
@AllArgsConstructor
public class CleaningDescriptionRepresentation {
    private final Long id;
    private final String standardCleaningDescription;
    private final String deepCleaningDescription;
    private final String postConstructionCleaningDescription;
    private final String disinfectionCleaningDescription;

    public CleaningDescription toDomain() {
        return new CleaningDescription.CleaningDescriptionBuilder()
                .withStandardCleaningDescription(standardCleaningDescription)
                .withDeepCleaningDescription(deepCleaningDescription)
                .withPostConstructionCleaningDescription(postConstructionCleaningDescription)
                .withDisinfectionCleaningDescription(disinfectionCleaningDescription)
                .build();
    }
}
