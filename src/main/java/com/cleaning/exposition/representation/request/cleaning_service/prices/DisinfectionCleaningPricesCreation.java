package com.cleaning.exposition.representation.request.cleaning_service.prices;

import com.cleaning.domain.cleaning_service.prices.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DisinfectionCleaningPricesCreation {
    private double disinfectionServicePrice;

    public DisinfectionCleaningPrice toDomain() {
        return new DisinfectionCleaningPrice(disinfectionServicePrice);
    }
}
