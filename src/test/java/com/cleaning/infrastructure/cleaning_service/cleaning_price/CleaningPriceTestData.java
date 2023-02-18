package com.cleaning.infrastructure.cleaning_service.cleaning_price;

import com.cleaning.domain.cleaning_service.prices.*;

public class CleaningPriceTestData {

    public static CleaningPrice dummyCleaningPrice() {
        return new CleaningPrice.CleaningPriceBuilder()
                .withStandardCleaningPrice(new StandardCleaningPrice(10.0, 10.0, 10.0, 10.0))
                .withDeepCleaningPrice(new DeepCleaningPrice(10.0, 10.0, 10.0, 10.0))
                .withPostConstructionCleaningPrice(new PostConstructionCleaningPrice(10.0, 10.0))
                .withDisinfectionCleaningPrice(new DisinfectionCleaningPrice(10.0))
                .withPaidParkingSpotPrice(10.0)
                .withPickUpKeysPrice(10.0)
                .build();
    }
}
