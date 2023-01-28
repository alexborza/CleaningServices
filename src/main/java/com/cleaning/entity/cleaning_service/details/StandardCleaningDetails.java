package com.cleaning.entity.cleaning_service.details;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
public class StandardCleaningDetails extends CleaningDetails {
    private int bedrooms;
    private int bathrooms;
    private int kitchens;

    public StandardCleaningDetails(String squareMeters, Parking parking, HomeAccess homeAccess, int bedrooms, int bathrooms, int kitchens) {
        super(squareMeters, parking, homeAccess);
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.kitchens = kitchens;
    }
}
