package com.cleaning.exposition.representation.response.cleaning_service.prices;

import com.cleaning.domain.cleaning_service.prices.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class DeepCleaningPricesRepresentation {
    private double deepServicePrice;
    private double deepServiceBedroom;
    private double deepServiceBathroom;
    private double deepServiceKitchen;

    private DeepCleaningPricesRepresentation(){}

    public static DeepCleaningPricesRepresentation emptyInstance() {

        return new DeepCleaningPricesRepresentation();
    }

    public static DeepCleaningPricesRepresentation fromDomain(DeepCleaningPrice deepCleaningPrice) {

        return new DeepCleaningPricesRepresentation(
                deepCleaningPrice.getDeepServicePrice(),
                deepCleaningPrice.getDeepServiceBedroom(),
                deepCleaningPrice.getDeepServiceBathroom(),
                deepCleaningPrice.getDeepServiceKitchen()
        );
    }
}
