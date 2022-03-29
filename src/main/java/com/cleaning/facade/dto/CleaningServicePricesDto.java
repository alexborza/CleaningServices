package com.cleaning.facade.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CleaningServicePricesDto {
    private Long id;
    private StandardCleaningPricesDto standardCleaningPrices;
    private DeepCleaningPricesDto deepCleaningPrices;
    private PostConstructionCleaningPricesDto postConstructionCleaningPrices;
    private DisinfectionCleaningPricesDto disinfectionCleaningPrices;
    private double paidParkingSpotPrice;
    private double pickUpKeysPrice;
}
