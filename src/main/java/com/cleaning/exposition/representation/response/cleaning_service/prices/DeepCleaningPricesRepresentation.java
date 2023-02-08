package com.cleaning.exposition.representation.response.cleaning_service.prices;

import com.cleaning.domain.cleaning_service.prices.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class DeepCleaningPricesRepresentation {
    private final double deepServicePrice;
    private final double deepServiceBedroom;
    private final double deepServiceBathroom;
    private final double deepServiceKitchen;

    public DeepCleaningPrice toDomain() {
        return new DeepCleaningPrice(
                deepServicePrice,
                deepServiceBedroom,
                deepServiceBathroom,
                deepServiceKitchen
        );
    }
}
