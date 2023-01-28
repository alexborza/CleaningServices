package com.cleaning.entity.cleaning_service.prices;

import lombok.*;

import javax.persistence.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DeepCleaningPrices {
    private Double deepServicePrice;
    private Double deepServiceBedroom;
    private Double deepServiceBathroom;
    private Double deepServiceKitchen;
}