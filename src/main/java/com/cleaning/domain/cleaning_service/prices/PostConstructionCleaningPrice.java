package com.cleaning.domain.cleaning_service.prices;

import com.cleaning.domain.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Embeddable
@NoArgsConstructor
@Getter
public class PostConstructionCleaningPrice extends BaseEntity {

    @NotNull
    private Double postConstructionServicePrice;

    @NotNull
    private Double roomPrice;

    public PostConstructionCleaningPrice(Double postConstructionServicePrice, Double roomPrice) {
        this.postConstructionServicePrice = postConstructionServicePrice;
        this.roomPrice = roomPrice;
        validate(this);
    }
}
