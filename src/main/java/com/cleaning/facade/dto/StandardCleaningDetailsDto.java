package com.cleaning.facade.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

@Data
@JsonTypeName("standardCleaning")
public class StandardCleaningDetailsDto extends CleaningDetailsDto {
    private int bedrooms;
    private int bathrooms;
    private int kitchens;
}
