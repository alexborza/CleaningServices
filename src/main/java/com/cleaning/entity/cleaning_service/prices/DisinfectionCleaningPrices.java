package com.cleaning.entity.cleaning_service.prices;

import lombok.*;

import javax.persistence.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DisinfectionCleaningPrices {
    private Double disinfectionServicePrice;
}
