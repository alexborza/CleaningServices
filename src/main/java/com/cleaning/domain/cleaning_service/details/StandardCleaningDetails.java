package com.cleaning.domain.cleaning_service.details;

import lombok.*;

import javax.persistence.*;

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
