package com.cleaning.exposition.representation.request.cleaning_service.details;

import com.cleaning.domain.cleaning_service.details.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@JsonTypeName("standardCleaning")
public class StandardCleaningDetailsCreation extends CleaningDetailsCreation {
    private final int bedrooms;
    private final int bathrooms;
    private final int kitchens;

    public StandardCleaningDetailsCreation(
            Double squareMeters,
            Parking parking,
            HomeAccess homeAccess,
            Integer bedrooms,
            Integer bathrooms,
            Integer kitchens) {
        super(squareMeters, parking, homeAccess);
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.kitchens = kitchens;
    }

    @Override
    public CleaningDetails toDomain() {

        return new StandardCleaningDetails(
                getSquareMeters(),
                getParking(),
                getHomeAccess(),
                bedrooms,
                bathrooms,
                kitchens
        );
    }
}
