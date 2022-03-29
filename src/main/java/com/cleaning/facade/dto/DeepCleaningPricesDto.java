package com.cleaning.facade.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeepCleaningPricesDto {
    private double deepServicePrice;
    private double deepServiceBedroom;
    private double deepServiceBathroom;
    private double deepServiceKitchen;
}
