package com.cleaning.entity.cleaning_service.details;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
