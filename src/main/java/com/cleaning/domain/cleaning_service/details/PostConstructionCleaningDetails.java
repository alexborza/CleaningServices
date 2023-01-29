package com.cleaning.domain.cleaning_service.details;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class PostConstructionCleaningDetails extends CleaningDetails {

    @Enumerated(EnumType.STRING)
    private Property property;
    private int rooms;

    public PostConstructionCleaningDetails(String squareMeters, Parking parking, HomeAccess homeAccess, Property property, int rooms) {
        super(squareMeters, parking, homeAccess);
        this.property = property;
        this.rooms = rooms;
    }
}
