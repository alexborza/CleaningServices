package com.cleaning.facade.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("standardCleaning")
public class StandardCleaningDetailsDto extends CleaningDetailsDto {
    private int bedrooms;
    private int bathrooms;
    private int kitchens;
}
