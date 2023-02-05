package com.cleaning.exposition.representation.response.cleaning_service.prices;

import lombok.*;

@AllArgsConstructor
@Getter
public class CleaningPricesRepresentation {
    private final Long id;
    private final StandardCleaningPricesRepresentation standardCleaningPrices;
    private final DeepCleaningPricesRepresentation deepCleaningPrices;
    private final PostConstructionCleaningPricesRepresentation postConstructionCleaningPrices;
    private final DisinfectionCleaningPricesRepresentation disinfectionCleaningPrices;
    private final double paidParkingSpotPrice;
    private final double pickUpKeysPrice;
}
