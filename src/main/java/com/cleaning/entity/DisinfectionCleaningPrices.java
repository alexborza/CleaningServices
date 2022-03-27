package com.cleaning.entity;

import lombok.*;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
public class DisinfectionCleaningPrices {
    private double disinfectionServicePrice;
}
