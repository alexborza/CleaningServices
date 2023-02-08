package com.cleaning.domain.cleaning_service.prices;

import lombok.*;

import javax.persistence.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DisinfectionCleaningPrice {
    private Double disinfectionServicePrice;
}
