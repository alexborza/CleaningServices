package com.cleaning.facade.dto;

import com.cleaning.entity.DayTimeOfficeCleaning;
import com.cleaning.entity.OfficeCleaningFrequency;
import com.cleaning.entity.PrimaryUseOfSpace;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SpaceDetailsDto {
    private final PrimaryUseOfSpace primaryUseOfSpace;
    private final String totalSquareMeters;
    private final String numberOfPersons;
    private final OfficeCleaningFrequency frequencyOfCleaning;
    private final DayTimeOfficeCleaning dayTime;
}
