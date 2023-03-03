package com.cleaning.exposition.representation.request.cleaning_service.prices;

import com.cleaning.domain.cleaning_service.prices.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class DisinfectionCleaningPricesCreation {
    private final double disinfectionServicePrice;

    public DisinfectionCleaningPrice toDomain() {
        return new DisinfectionCleaningPrice(disinfectionServicePrice);
    }
}
