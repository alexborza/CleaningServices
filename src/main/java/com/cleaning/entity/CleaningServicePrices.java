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

    public static CleaningServicePrices emptyInstance(){
        CleaningServicePrices instance = new CleaningServicePrices();
        instance.id = null;
        instance.standardCleaningPrices = new StandardCleaningPrices();
        instance.deepCleaningPrices = new DeepCleaningPrices();
        instance.disinfectionCleaningPrices = new DisinfectionCleaningPrices();
        instance.postConstructionCleaningPrices = new PostConstructionCleaningPrices();
        return instance;
    }

    public void mapCleaningServicePrices(CleaningServicePrices other){
        this.standardCleaningPrices = other.getStandardCleaningPrices();
        this.deepCleaningPrices = other.getDeepCleaningPrices();
        this.postConstructionCleaningPrices = other.getPostConstructionCleaningPrices();
        this.disinfectionCleaningPrices = other.getDisinfectionCleaningPrices();
        this.paidParkingSpotPrice = other.getPaidParkingSpotPrice();
        this.pickUpKeysPrice = other.pickUpKeysPrice;
    }
}
