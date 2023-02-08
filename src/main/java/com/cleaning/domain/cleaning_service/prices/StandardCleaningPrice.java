package com.cleaning.domain.cleaning_service.prices;

import lombok.*;

import javax.persistence.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StandardCleaningPrice {
    private Double standardServicePrice;
    private Double standardServiceBedroom;
    private Double standardServiceBathroom;
    private Double standardServiceKitchen;
}
