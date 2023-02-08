package com.cleaning.exposition.representation.response.cleaning_service.prices;

import com.cleaning.domain.cleaning_service.prices.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class DisinfectionCleaningPricesRepresentation {
    private final double disinfectionServicePrice;

    public DisinfectionCleaningPrice toDomain() {
        return new DisinfectionCleaningPrice(disinfectionServicePrice);
    }
}
