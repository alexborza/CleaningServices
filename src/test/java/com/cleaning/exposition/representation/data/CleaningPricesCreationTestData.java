package com.cleaning.exposition.representation.data;

import com.cleaning.exposition.representation.request.cleaning_service.prices.*;

public class CleaningPricesCreationTestData {

    public static CleaningPriceCreation dummyCleaningPricesRepresentation() {
        return new CleaningPriceCreation(
                new StandardCleaningPricesCreation(10.0, 10.0, 10.0, 10.0),
                new DeepCleaningPricesCreation(10.0, 10.0, 10.0, 10.0),
                new PostConstructionCleaningPricesCreation(20.0, 15.0),
                new DisinfectionCleaningPricesCreation(15.0),
                20.0,
                15.0
        );
    }
}
