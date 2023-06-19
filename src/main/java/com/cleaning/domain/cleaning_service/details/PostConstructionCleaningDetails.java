package com.cleaning.domain.cleaning_service.details;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@NoArgsConstructor
@Getter
public class PostConstructionCleaningDetails extends CleaningDetails {

    @Enumerated(EnumType.STRING)
    @NotNull
    private Property property;

    @NotNull
    @Min(1)
    private Integer rooms;

    public PostConstructionCleaningDetails(String squareMeters, Parking parking, HomeAccess homeAccess, Property property, Integer rooms) {
        super(squareMeters, parking, homeAccess);
        this.property = property;
        this.rooms = rooms;
        validate(this);
    }
}
