package com.cleaning.domain.cleaning_service.prices;

import com.cleaning.domain.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Embeddable
@NoArgsConstructor
@Getter
public class StandardCleaningPrice extends BaseEntity {

    @NotNull
    private Double standardServicePrice;

    @NotNull
    private Double standardServiceBedroom;

    @NotNull
    private Double standardServiceBathroom;

    @NotNull
    private Double standardServiceKitchen;

    public StandardCleaningPrice(Double standardServicePrice, Double standardServiceBedroom, Double standardServiceBathroom, Double standardServiceKitchen) {
        this.standardServicePrice = standardServicePrice;
        this.standardServiceBedroom = standardServiceBedroom;
        this.standardServiceBathroom = standardServiceBathroom;
        this.standardServiceKitchen = standardServiceKitchen;
        validate(this);
    }
}
