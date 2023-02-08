package com.cleaning.exposition.representation.data;

import com.cleaning.exposition.representation.response.cleaning_service.prices.*;

public class CleaningPricesRepresentationTestData {

    public static CleaningPricesRepresentation dummyCleaningPricesRepresentation() {
        return new CleaningPricesRepresentation(
                1L,
                new StandardCleaningPricesRepresentation(10.0, 10.0, 10.0, 10.0),
                new DeepCleaningPricesRepresentation(10.0, 10.0, 10.0, 10.0),
                new PostConstructionCleaningPricesRepresentation(20.0, 15.0),
                new DisinfectionCleaningPricesRepresentation(15.0),
                20.0,
                15.0
        );
    }
}
