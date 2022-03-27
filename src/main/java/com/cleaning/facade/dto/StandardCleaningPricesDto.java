package com.cleaning.facade.dto;

import lombok.*;

@Getter
@AllArgsConstructor
public class StandardCleaningPricesDto {
    private final double standardServicePrice;
    private final double standardServiceBedroom;
    private final double standardServiceBathroom;
    private final double standardServiceKitchen;
}
