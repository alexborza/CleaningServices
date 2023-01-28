package com.cleaning.entity.cleaning_service.prices;

import lombok.*;

import javax.persistence.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StandardCleaningPrices {
    private Double standardServicePrice;
    private Double standardServiceBedroom;
    private Double standardServiceBathroom;
    private Double standardServiceKitchen;
}
