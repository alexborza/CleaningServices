package com.cleaning.exposition.representation.request.cleaning_service.details;

import com.cleaning.domain.cleaning_service.details.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@JsonTypeName("postConstructionCleaning")
public class PostConstructionCleaningDetailsCreation extends CleaningDetailsCreation {
    private final Property property;
    private final Integer rooms;


    public PostConstructionCleaningDetailsCreation(
            Long id,
            Double squareMeters,
            Parking parking,
            HomeAccess homeAccess,
            Property property,
            Integer rooms) {

        super(id, squareMeters, parking, homeAccess);
        this.property = property;
        this.rooms = rooms;
    }

    @Override
    public CleaningDetails toDomain() {

        return new PostConstructionCleaningDetails(
                getSquareMeters(),
                getParking(),
                getHomeAccess(),
                property,
                rooms
        );
    }
}
