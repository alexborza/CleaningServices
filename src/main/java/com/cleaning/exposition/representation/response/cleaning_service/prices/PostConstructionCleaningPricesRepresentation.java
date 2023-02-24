package com.cleaning.exposition.representation.response.cleaning_service.prices;

import com.cleaning.domain.cleaning_service.prices.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class PostConstructionCleaningPricesRepresentation {
    private double postConstructionServicePrice;
    private double roomPrice;

    private PostConstructionCleaningPricesRepresentation(){}

    public static PostConstructionCleaningPricesRepresentation emptyInstance() {

        return new PostConstructionCleaningPricesRepresentation();
    }

    public static PostConstructionCleaningPricesRepresentation fromDomain(PostConstructionCleaningPrice postConstructionCleaningPrice) {

        return new PostConstructionCleaningPricesRepresentation(
                postConstructionCleaningPrice.getPostConstructionServicePrice(),
                postConstructionCleaningPrice.getRoomPrice()
        );
    }

    public PostConstructionCleaningPrice toDomain() {
        return new PostConstructionCleaningPrice(
                postConstructionServicePrice,
                roomPrice
        );
    }
}
