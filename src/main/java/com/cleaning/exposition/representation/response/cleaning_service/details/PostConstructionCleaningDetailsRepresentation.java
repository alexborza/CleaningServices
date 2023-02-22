package com.cleaning.exposition.representation.response.cleaning_service.details;

import com.cleaning.domain.cleaning_service.details.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@JsonTypeName("postConstructionCleaning")
public class PostConstructionCleaningDetailsRepresentation extends CleaningDetailsRepresentation {
    private final Property property;
    private final Integer rooms;


    public PostConstructionCleaningDetailsRepresentation(
            Long id,
            String squareMeters,
            Parking parking,
            HomeAccess homeAccess,
            Property property,
            Integer rooms) {

        super(id, squareMeters, parking, homeAccess);
        this.property = property;
        this.rooms = rooms;
    }

    public static PostConstructionCleaningDetailsRepresentation fromDomain(PostConstructionCleaningDetails cleaningDetails) {

        return new PostConstructionCleaningDetailsRepresentation(
                cleaningDetails.getId(),
                cleaningDetails.getSquareMeters(),
                cleaningDetails.getParking(),
                cleaningDetails.getHomeAccess(),
                cleaningDetails.getProperty(),
                cleaningDetails.getRooms()
        );
    }
}
