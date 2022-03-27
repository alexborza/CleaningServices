package com.cleaning.entity;

import lombok.*;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
public class StandardCleaningPrices {
    private double standardServicePrice;
    private double standardServiceBedroom;
    private double standardServiceBathroom;
    private double standardServiceKitchen;
}
