package com.cleaning.facade.dto;

import com.cleaning.entity.HomeAccess;
import com.cleaning.entity.Parking;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = StandardCleaningDetailsDto.class, name = "standardCleaning"),
        @JsonSubTypes.Type(value = DisinfectionCleaningDetailsDto.class, name = "disinfectionCleaning"),
        @JsonSubTypes.Type(value = PostConstructionCleaningDetailsDto.class, name = "postConstructionCleaning")
})
public abstract class CleaningDetailsDto {
    private int squareMeters;
    private Parking parking;
    private HomeAccess homeAccess;
}
