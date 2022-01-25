package com.cleaning.facade.dto;

import com.cleaning.entity.Property;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("postConstructionCleaning")
public class PostConstructionCleaningDetailsDto extends CleaningDetailsDto{
    private Property property;
    private int rooms;
}
