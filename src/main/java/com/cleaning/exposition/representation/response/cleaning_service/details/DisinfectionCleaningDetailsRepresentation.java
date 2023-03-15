package com.cleaning.exposition.representation.response.cleaning_service.details;

import com.cleaning.domain.cleaning_service.details.*;
import lombok.*;

@Getter
public class DisinfectionCleaningDetailsRepresentation extends CleaningDetailsRepresentation {
    private final Property property;

    public DisinfectionCleaningDetailsRepresentation(
            Long id,
            Double squareMeters,
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
}
