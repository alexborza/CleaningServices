package com.cleaning.facade.dto;

import lombok.*;

@Getter
@AllArgsConstructor
public class PostConstructionCleaningPricesDto {
    private final double postConstructionServicePrice;
    private final double roomPrice;
}
