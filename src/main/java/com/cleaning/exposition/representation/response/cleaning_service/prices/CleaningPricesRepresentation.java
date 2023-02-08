package com.cleaning.exposition.representation.response.cleaning_service.prices;

import com.cleaning.domain.cleaning_service.prices.*;
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

    public CleaningPrice toDomain() {
        return new CleaningPrice.CleaningPriceBuilder()
                .withStandardCleaningPrice(standardCleaningPrices.toDomain())
                .withDeepCleaningPrice(deepCleaningPrices.toDomain())
                .withPostConstructionCleaningPrice(postConstructionCleaningPrices.toDomain())
                .withDisinfectionCleaningPrice(disinfectionCleaningPrices.toDomain())
                .withPaidParkingSpotPrice(paidParkingSpotPrice)
                .withPickUpKeysPrice(pickUpKeysPrice)
                .build();
    }
}
