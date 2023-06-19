package com.cleaning.domain.cleaning_service.prices;

import com.cleaning.domain.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Embeddable
@NoArgsConstructor
@Getter
public class DisinfectionCleaningPrice extends BaseEntity {
    @NotNull
    private Double disinfectionServicePrice;

    public DisinfectionCleaningPrice(Double disinfectionServicePrice) {
        this.disinfectionServicePrice = disinfectionServicePrice;
        validate(this);
    }
}
