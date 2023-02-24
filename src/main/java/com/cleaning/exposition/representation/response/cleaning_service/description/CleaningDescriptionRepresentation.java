package com.cleaning.exposition.representation.response.cleaning_service.description;

import com.cleaning.domain.cleaning_service.description.*;
import lombok.*;


@Getter
@AllArgsConstructor
public class CleaningDescriptionRepresentation {
    private Long id;
    private String standardCleaningDescription;
    private String deepCleaningDescription;
    private String postConstructionCleaningDescription;
    private String disinfectionCleaningDescription;

    private CleaningDescriptionRepresentation(){

    }

    public static CleaningDescriptionRepresentation emptyInstance() {
        return new CleaningDescriptionRepresentation();
    }

    public static CleaningDescriptionRepresentation fromDomain(CleaningDescription cleaningDescription) {

        return new CleaningDescriptionRepresentation(
                cleaningDescription.getId(),
                cleaningDescription.getStandardCleaningDescription(),
                cleaningDescription.getDeepCleaningDescription(),
                cleaningDescription.getPostConstructionCleaningDescription(),
                cleaningDescription.getDisinfectionCleaningDescription()
        );
    }

    public CleaningDescription toDomain() {
        return new CleaningDescription.CleaningDescriptionBuilder()
                .withStandardCleaningDescription(standardCleaningDescription)
                .withDeepCleaningDescription(deepCleaningDescription)
                .withPostConstructionCleaningDescription(postConstructionCleaningDescription)
                .withDisinfectionCleaningDescription(disinfectionCleaningDescription)
                .build();
    }
}
