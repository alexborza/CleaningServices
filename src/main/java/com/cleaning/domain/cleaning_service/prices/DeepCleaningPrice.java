package com.cleaning.domain.cleaning_service.prices;

import com.cleaning.domain.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Embeddable
@NoArgsConstructor
@Getter
public class DeepCleaningPrice extends BaseEntity {

    @NotNull
    private Double deepServicePrice;

    @NotNull
    private Double deepServiceBedroom;

    @NotNull
    private Double deepServiceBathroom;

    @NotNull
    private Double deepServiceKitchen;

    public DeepCleaningPrice(Double deepServicePrice, Double deepServiceBedroom, Double deepServiceBathroom, Double deepServiceKitchen) {
        this.deepServicePrice = deepServicePrice;
        this.deepServiceBedroom = deepServiceBedroom;
        this.deepServiceBathroom = deepServiceBathroom;
        this.deepServiceKitchen = deepServiceKitchen;
        validate(this);
    }
}