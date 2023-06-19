package com.cleaning.domain.cleaning_service.details;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@NoArgsConstructor
@Getter
public class DisinfectionCleaningDetails extends CleaningDetails {

    @Enumerated(EnumType.STRING)
    @NotNull
    private Property property;

    public DisinfectionCleaningDetails(String squareMeters, Parking parking, HomeAccess homeAccess, Property property) {
        super(squareMeters, parking, homeAccess);
        this.property = property;
        validate(this);
    }
}
