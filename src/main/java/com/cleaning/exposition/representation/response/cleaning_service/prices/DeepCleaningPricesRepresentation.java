package com.cleaning.exposition.representation.response.cleaning_service.prices;

import lombok.*;

@AllArgsConstructor
@Getter
public class DeepCleaningPricesRepresentation {
    private final double deepServicePrice;
    private final double deepServiceBedroom;
    private final double deepServiceBathroom;
    private final double deepServiceKitchen;
}
