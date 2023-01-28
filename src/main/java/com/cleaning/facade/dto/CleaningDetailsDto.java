package com.cleaning.facade.dto;

import com.cleaning.entity.cleaning_service.details.HomeAccess;
import com.cleaning.entity.cleaning_service.details.Parking;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = StandardCleaningDetailsDto.class, name = "standardCleaning"),
        @JsonSubTypes.Type(value = DisinfectionCleaningDetailsDto.class, name = "disinfectionCleaning"),
        @JsonSubTypes.Type(value = PostConstructionCleaningDetailsDto.class, name = "postConstructionCleaning")
})
public abstract class CleaningDetailsDto {
    private Long id;
    private String squareMeters;
    private Parking parking;
    private HomeAccess homeAccess;
}
