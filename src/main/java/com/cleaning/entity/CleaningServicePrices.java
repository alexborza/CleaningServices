package com.cleaning.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CleaningServicePrices {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private StandardCleaningPrices standardCleaningPrices;

    @Embedded
    private DeepCleaningPrices deepCleaningPrices;

    @Embedded
    private PostConstructionCleaningPrices postConstructionCleaningPrices;

    @Embedded
    private DisinfectionCleaningPrices disinfectionCleaningPrices;

    private double paidParkingSpotPrice;
    private double pickUpKeysPrice;
}
