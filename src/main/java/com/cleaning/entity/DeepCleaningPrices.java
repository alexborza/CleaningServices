package com.cleaning.entity;

import lombok.*;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
public class DeepCleaningPrices {
    private double deepServicePrice;
    private double deepServiceBedroom;
    private double deepServiceBathroom;
    private double deepServiceKitchen;
}