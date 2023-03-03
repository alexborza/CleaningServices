package com.cleaning.exposition.representation.request.cleaning_service.prices;

import com.cleaning.domain.cleaning_service.prices.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class CleaningPriceCreation {

    private StandardCleaningPricesCreation standardCleaningPrices;
    private DeepCleaningPricesCreation deepCleaningPrices;
    private PostConstructionCleaningPricesCreation postConstructionCleaningPrices;
    private DisinfectionCleaningPricesCreation disinfectionCleaningPrices;
    private double paidParkingSpotPrice;
    private double pickUpKeysPrice;

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
