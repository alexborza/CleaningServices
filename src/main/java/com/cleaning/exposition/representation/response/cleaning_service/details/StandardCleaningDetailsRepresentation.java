package com.cleaning.exposition.representation.response.cleaning_service.details;

import com.cleaning.domain.cleaning_service.details.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@JsonTypeName("standardCleaning")
public class StandardCleaningDetailsRepresentation extends CleaningDetailsRepresentation {
    private final int bedrooms;
    private final int bathrooms;
    private final int kitchens;

    public StandardCleaningDetailsRepresentation(
            Long id,
            String squareMeters,
            Parking parking,
            HomeAccess homeAccess,
            Integer bedrooms,
            Integer bathrooms,
            Integer kitchens) {
        super(id, squareMeters, parking, homeAccess);
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.kitchens = kitchens;
    }
}
