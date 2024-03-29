package com.cleaning.exposition.representation.request.cleaning_service.prices;

import com.cleaning.domain.cleaning_service.prices.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class PostConstructionCleaningPricesCreation {
    private final double postConstructionServicePrice;
    private final double roomPrice;

    public PostConstructionCleaningPrice toDomain() {
        return new PostConstructionCleaningPrice(
                postConstructionServicePrice,
                roomPrice
        );
    }
}
