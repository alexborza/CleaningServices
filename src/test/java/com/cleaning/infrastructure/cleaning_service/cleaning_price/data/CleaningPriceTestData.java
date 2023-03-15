package com.cleaning.infrastructure.cleaning_service.cleaning_price.data;

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

    public static CleaningPrice dummyCleaningPrice(Double standardPrice, Double deepPrice, Double postConstructionPrice, Double disinfectionPrice) {
        return new CleaningPrice.CleaningPriceBuilder()
                .withStandardCleaningPrice(new StandardCleaningPrice(standardPrice, standardPrice, standardPrice, standardPrice))
                .withDeepCleaningPrice(new DeepCleaningPrice(deepPrice, deepPrice, deepPrice, deepPrice))
                .withPostConstructionCleaningPrice(new PostConstructionCleaningPrice(postConstructionPrice, postConstructionPrice))
                .withDisinfectionCleaningPrice(new DisinfectionCleaningPrice(disinfectionPrice))
                .withPaidParkingSpotPrice(10.0)
                .withPickUpKeysPrice(10.0)
                .build();
    }

    public static CleaningPrice dummyCleaningPrice(StandardCleaningPrice standardPrice, DeepCleaningPrice deepPrice, PostConstructionCleaningPrice postConstructionPrice, DisinfectionCleaningPrice disinfectionPrice) {
        return new CleaningPrice.CleaningPriceBuilder()
                .withStandardCleaningPrice(standardPrice)
                .withDeepCleaningPrice(deepPrice)
                .withPostConstructionCleaningPrice(postConstructionPrice)
                .withDisinfectionCleaningPrice(disinfectionPrice)
                .withPaidParkingSpotPrice(10.0)
                .withPickUpKeysPrice(10.0)
                .build();
    }

    public static CleaningPrice dummyCleaningPriceWithPaidAndPickUp(Double paidParkingSpotPrice, Double pickupKeysPrice, Double standardPrice, Double deepPrice, Double postConstructionPrice, Double disinfectionPrice) {
        return new CleaningPrice.CleaningPriceBuilder()
                .withStandardCleaningPrice(new StandardCleaningPrice(standardPrice, standardPrice, standardPrice, standardPrice))
                .withDeepCleaningPrice(new DeepCleaningPrice(deepPrice, deepPrice, deepPrice, deepPrice))
                .withPostConstructionCleaningPrice(new PostConstructionCleaningPrice(postConstructionPrice, postConstructionPrice))
                .withDisinfectionCleaningPrice(new DisinfectionCleaningPrice(disinfectionPrice))
                .withPaidParkingSpotPrice(paidParkingSpotPrice)
                .withPickUpKeysPrice(pickupKeysPrice)
                .build();
    }

    public static StandardCleaningPrice dummyStandardCleaningPrice(Double price, Double kitchenPrice, Double bedroomPrice, Double bathroomPrice) {
        return new StandardCleaningPrice(
                price,
                bedroomPrice,
                bathroomPrice,
                kitchenPrice
        );
    }

    public static DeepCleaningPrice dummyDeepCleaningPrice(Double price, Double kitchenPrice, Double bedroomPrice, Double bathroomPrice) {
        return new DeepCleaningPrice(
                price,
                bedroomPrice,
                bathroomPrice,
                kitchenPrice
        );
    }

    public static PostConstructionCleaningPrice dummyPostConstructionCleaningPrice(Double price, Double roomPrice) {
        return new PostConstructionCleaningPrice(
                price,
                roomPrice
        );
    }

    public static DisinfectionCleaningPrice dummyDisinfectionCleaningPrice(Double price) {
        return new DisinfectionCleaningPrice(
                price
        );
    }
}
