package com.cleaning.exposition.representation.response.cleaning_service.prices;

import com.cleaning.domain.cleaning_service.prices.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class StandardCleaningPricesRepresentation {
    private double standardServicePrice;
    private double standardServiceBedroom;
    private double standardServiceBathroom;
    private double standardServiceKitchen;

    private StandardCleaningPricesRepresentation(){}

    public static StandardCleaningPricesRepresentation emptyInstance() {

        return new StandardCleaningPricesRepresentation();
    }

    public static StandardCleaningPricesRepresentation fromDomain(StandardCleaningPrice standardCleaningPrice) {

        return new StandardCleaningPricesRepresentation(
                standardCleaningPrice.getStandardServicePrice(),
                standardCleaningPrice.getStandardServiceBedroom(),
                standardCleaningPrice.getStandardServiceBathroom(),
                standardCleaningPrice.getStandardServiceKitchen()
        );
    }

    public StandardCleaningPrice toDomain() {
        return new StandardCleaningPrice(
                standardServicePrice,
                standardServiceBedroom,
                standardServiceBathroom,
                standardServiceKitchen
        );
    }
}
