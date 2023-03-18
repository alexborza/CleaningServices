package com.cleaning.exposition.representation.request.cleaning_service.details;

import com.cleaning.domain.cleaning_service.details.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

@AllArgsConstructor
@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = StandardCleaningDetailsCreation.class, name = "standardCleaning"),
        @JsonSubTypes.Type(value = DisinfectionCleaningDetailsCreation.class, name = "disinfectionCleaning"),
        @JsonSubTypes.Type(value = PostConstructionCleaningDetailsCreation.class, name = "postConstructionCleaning")
})
public abstract class CleaningDetailsCreation {
    private final Double squareMeters;
    private final Parking parking;
    private final HomeAccess homeAccess;

    public abstract CleaningDetails toDomain();
}
