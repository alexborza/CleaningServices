package com.cleaning.exposition.representation.response.cleaning_service.details;

import com.cleaning.domain.cleaning_service.details.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

@AllArgsConstructor
@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = StandardCleaningDetailsRepresentation.class, name = "standardCleaning"),
        @JsonSubTypes.Type(value = DisinfectionCleaningDetailsRepresentation.class, name = "disinfectionCleaning"),
        @JsonSubTypes.Type(value = PostConstructionCleaningDetailsRepresentation.class, name = "postConstructionCleaning")
})
public abstract class CleaningDetailsRepresentation {
    private final Long id;
    private final String squareMeters;
    private final Parking parking;
    private final HomeAccess homeAccess;

    public abstract CleaningDetails toDomain();

    public static CleaningDetailsRepresentation fromDomain(CleaningDetails cleaningDetails) {
        if(cleaningDetails instanceof StandardCleaningDetails)
            return StandardCleaningDetailsRepresentation.fromDomain((StandardCleaningDetails) cleaningDetails);
        else if(cleaningDetails instanceof DisinfectionCleaningDetails)
            return DisinfectionCleaningDetailsRepresentation.fromDomain((DisinfectionCleaningDetails) cleaningDetails);
        else if(cleaningDetails instanceof PostConstructionCleaningDetails)
            return PostConstructionCleaningDetailsRepresentation.fromDomain((PostConstructionCleaningDetails) cleaningDetails);

        throw new IllegalArgumentException("Unknown subtype of CleaningDetailsRepresentation");
    }
}
