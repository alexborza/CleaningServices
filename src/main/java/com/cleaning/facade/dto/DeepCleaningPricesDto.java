package com.cleaning.facade.dto;

import lombok.*;

@Getter
@AllArgsConstructor
public class DeepCleaningPricesDto {
    private final double deepServicePrice;
    private final double deepServiceBedroom;
    private final double deepServiceBathroom;
    private final double deepServiceKitchen;
}
