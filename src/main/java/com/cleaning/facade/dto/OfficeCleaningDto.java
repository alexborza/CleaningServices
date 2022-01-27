package com.cleaning.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OfficeCleaningDto {
    private final Long id;
    private final SpaceDetailsDto spaceDetails;
    private final SpaceTypeDto spaceType;
    private final ContactInfoDto contactInfo;
    private final LocationDto location;
}
