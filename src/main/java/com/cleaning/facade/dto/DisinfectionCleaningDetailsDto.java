package com.cleaning.facade.dto;

import com.cleaning.entity.Property;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

@Data
@JsonTypeName("disinfectionCleaning")
public class DisinfectionCleaningDetailsDto extends CleaningDetailsDto{
    private Property property;
}
