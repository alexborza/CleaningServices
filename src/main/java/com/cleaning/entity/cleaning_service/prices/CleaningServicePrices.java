package com.cleaning.entity.cleaning_service.prices;

import lombok.*;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Getter
public class CleaningServicePrices {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private final StandardCleaningPrices standardCleaningPrices;

    @Embedded
    private final DeepCleaningPrices deepCleaningPrices;

    @Embedded
    private final PostConstructionCleaningPrices postConstructionCleaningPrices;

    @Embedded
    private final DisinfectionCleaningPrices disinfectionCleaningPrices;

    private final Double paidParkingSpotPrice;
    private final Double pickUpKeysPrice;

    private CleaningServicePrices() {
        this.standardCleaningPrices = null;
        this.deepCleaningPrices = null;
        this.postConstructionCleaningPrices = null;
        this.disinfectionCleaningPrices = null;
        this.paidParkingSpotPrice = null;
        this.pickUpKeysPrice = null;
    }
}
