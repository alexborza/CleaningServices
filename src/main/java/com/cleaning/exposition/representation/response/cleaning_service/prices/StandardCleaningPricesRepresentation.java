package com.cleaning.exposition.representation.response.cleaning_service.prices;

import lombok.*;

@AllArgsConstructor
@Getter
public class StandardCleaningPricesRepresentation {
    private final double standardServicePrice;
    private final double standardServiceBedroom;
    private final double standardServiceBathroom;
    private final double standardServiceKitchen;
}
