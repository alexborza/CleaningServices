package com.cleaning.exposition.representation.response.cleaning_service.prices;

import com.cleaning.domain.cleaning_service.prices.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class DisinfectionCleaningPricesRepresentation {
    private double disinfectionServicePrice;

    private DisinfectionCleaningPricesRepresentation(){}

    public static DisinfectionCleaningPricesRepresentation emptyInstance() {

        return new DisinfectionCleaningPricesRepresentation();
    }

    public static DisinfectionCleaningPricesRepresentation fromDomain(DisinfectionCleaningPrice disinfectionCleaningPrice) {

        return new DisinfectionCleaningPricesRepresentation(disinfectionCleaningPrice.getDisinfectionServicePrice());
    }

    public DisinfectionCleaningPrice toDomain() {
        return new DisinfectionCleaningPrice(disinfectionServicePrice);
    }
}
