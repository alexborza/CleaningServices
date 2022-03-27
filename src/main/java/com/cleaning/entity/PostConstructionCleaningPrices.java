package com.cleaning.entity;

import lombok.*;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
public class PostConstructionCleaningPrices {
    private double postConstructionServicePrice;
    private double roomPrice;
}
