package com.cleaning.exposition.representation.response.cleaning_service.details;

import com.cleaning.domain.cleaning_service.details.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@JsonTypeName("disinfectionCleaning")
public class DisinfectionCleaningDetailsRepresentation extends CleaningDetailsRepresentation {
    private final Property property;

    public DisinfectionCleaningDetailsRepresentation(
            Long id,
            String squareMeters,
            Parking parking,
            HomeAccess homeAccess,
            Property property) {
        super(id, squareMeters, parking, homeAccess);
        this.property = property;
    }

    public static DisinfectionCleaningDetailsRepresentation fromDomain(DisinfectionCleaningDetails cleaningDetails) {

        return new DisinfectionCleaningDetailsRepresentation(
                cleaningDetails.getId(),
                cleaningDetails.getSquareMeters(),
                cleaningDetails.getParking(),
                cleaningDetails.getHomeAccess(),
                cleaningDetails.getProperty()
        );
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
