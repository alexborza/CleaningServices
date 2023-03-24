package com.cleaning.exposition.representation.request.cleaning_service.details;

import com.cleaning.domain.cleaning_service.details.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@JsonTypeName("disinfectionCleaning")
public class DisinfectionCleaningDetailsCreation extends CleaningDetailsCreation {
    private final Property property;

    public DisinfectionCleaningDetailsCreation(
            String squareMeters,
            Parking parking,
            HomeAccess homeAccess,
            Property property) {
        super(squareMeters, parking, homeAccess);
        this.property = property;
    }

    @Override
    public CleaningDetails toDomain() {

        return new DisinfectionCleaningDetails(
                getSquareMeters(),
                getParking(),
                getHomeAccess(),
                property
        );
    }
}
