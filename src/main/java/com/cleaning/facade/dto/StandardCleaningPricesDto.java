package com.cleaning.facade.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandardCleaningPricesDto {
    private double standardServicePrice;
    private double standardServiceBedroom;
    private double standardServiceBathroom;
    private double standardServiceKitchen;
}
