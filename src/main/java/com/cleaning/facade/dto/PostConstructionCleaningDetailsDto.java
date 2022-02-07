package com.cleaning.facade.dto;

import com.cleaning.entity.Property;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

@Data
@JsonTypeName("postConstructionCleaning")
public class PostConstructionCleaningDetailsDto extends CleaningDetailsDto{
    private Property property;
    private int rooms;
}
