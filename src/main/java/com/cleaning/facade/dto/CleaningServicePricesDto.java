package com.cleaning.facade.dto;

import lombok.*;

@Getter
@AllArgsConstructor
public class CleaningServicePricesDto {
    private final Long id;
    private final StandardCleaningPricesDto standardCleaningPrices;
    private final DeepCleaningPricesDto deepCleaningPrices;
    private final PostConstructionCleaningPricesDto postConstructionCleaningPrices;
    private final DisinfectionCleaningPricesDto disinfectionCleaningPrices;
    private final double paidParkingSpotPrice;
    private final double pickUpKeysPrice;
}
