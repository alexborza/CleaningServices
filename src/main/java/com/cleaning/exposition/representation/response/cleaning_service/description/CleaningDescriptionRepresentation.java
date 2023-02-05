package com.cleaning.exposition.representation.response.cleaning_service.description;

import lombok.*;


@Getter
@AllArgsConstructor
public class CleaningDescriptionRepresentation {
    private final Long id;
    private final String standardCleaningDescription;
    private final String deepCleaningDescription;
    private final String postConstructionCleaningDescription;
    private final String disinfectionCleaningDescription;
}
