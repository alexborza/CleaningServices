package com.cleaning.exposition.representation.response.cleaning_service.prices;

import com.cleaning.domain.cleaning_service.prices.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class StandardCleaningPricesRepresentation {
    private final double standardServicePrice;
    private final double standardServiceBedroom;
    private final double standardServiceBathroom;
    private final double standardServiceKitchen;

    public StandardCleaningPrice toDomain() {
        return new StandardCleaningPrice(
                standardServicePrice,
                standardServiceBedroom,
                standardServiceBathroom,
                standardServiceKitchen
        );
    }
}
