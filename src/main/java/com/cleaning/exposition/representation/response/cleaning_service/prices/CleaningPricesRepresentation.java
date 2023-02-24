package com.cleaning.exposition.representation.response.cleaning_service.prices;

import com.cleaning.domain.cleaning_service.prices.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class CleaningPricesRepresentation {
    private Long id;
    private StandardCleaningPricesRepresentation standardCleaningPrices;
    private DeepCleaningPricesRepresentation deepCleaningPrices;
    private PostConstructionCleaningPricesRepresentation postConstructionCleaningPrices;
    private DisinfectionCleaningPricesRepresentation disinfectionCleaningPrices;
    private double paidParkingSpotPrice;
    private double pickUpKeysPrice;

    private CleaningPricesRepresentation() {}

    public static CleaningPricesRepresentation emptyInstance() {
        CleaningPricesRepresentation cleaningPricesRepresentation = new CleaningPricesRepresentation();
        cleaningPricesRepresentation.standardCleaningPrices = StandardCleaningPricesRepresentation.emptyInstance();
        cleaningPricesRepresentation.deepCleaningPrices = DeepCleaningPricesRepresentation.emptyInstance();
        cleaningPricesRepresentation.postConstructionCleaningPrices = PostConstructionCleaningPricesRepresentation.emptyInstance();
        cleaningPricesRepresentation.disinfectionCleaningPrices = DisinfectionCleaningPricesRepresentation.emptyInstance();

        return cleaningPricesRepresentation;
    }

    public static CleaningPricesRepresentation fromDomain(CleaningPrice cleaningPrice) {

        return new CleaningPricesRepresentation(
                cleaningPrice.getId(),
                StandardCleaningPricesRepresentation.fromDomain(cleaningPrice.getStandardCleaningPrice()),
                DeepCleaningPricesRepresentation.fromDomain(cleaningPrice.getDeepCleaningPrice()),
                PostConstructionCleaningPricesRepresentation.fromDomain(cleaningPrice.getPostConstructionCleaningPrice()),
                DisinfectionCleaningPricesRepresentation.fromDomain(cleaningPrice.getDisinfectionCleaningPrice()),
                cleaningPrice.getPaidParkingSpotPrice(),
                cleaningPrice.getPickUpKeysPrice()
        );
    }

    public CleaningPrice toDomain() {

        return new CleaningPrice.CleaningPriceBuilder()
                .withStandardCleaningPrice(standardCleaningPrices.toDomain())
                .withDeepCleaningPrice(deepCleaningPrices.toDomain())
                .withPostConstructionCleaningPrice(postConstructionCleaningPrices.toDomain())
                .withDisinfectionCleaningPrice(disinfectionCleaningPrices.toDomain())
                .withPaidParkingSpotPrice(paidParkingSpotPrice)
                .withPickUpKeysPrice(pickUpKeysPrice)
                .build();
    }
}
