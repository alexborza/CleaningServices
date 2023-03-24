package com.cleaning.domain.cleaning_service.details;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@NoArgsConstructor
@Getter
public class StandardCleaningDetails extends CleaningDetails {

    @NotNull
    @Min(1)
    private Integer bedrooms;

    @NotNull
    @Min(1)
    private Integer bathrooms;

    @NotNull
    @Min(1)
    private Integer kitchens;

    public StandardCleaningDetails(String squareMeters, Parking parking, HomeAccess homeAccess, Integer bedrooms, Integer bathrooms, Integer kitchens) {
        super(squareMeters, parking, homeAccess);
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.kitchens = kitchens;
        validate(this);
    }
}
