package com.cleaning.facade.dto;

import com.cleaning.entity.DayTimeOfficeCleaning;
import com.cleaning.entity.OfficeCleaningFrequency;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@AllArgsConstructor
public class SpaceDetailsDto {
    private final int totalSquareMeters;
    private final String numberOfPersons;
    private final OfficeCleaningFrequency frequencyOfCleaning;
    private final DayTimeOfficeCleaning dayTime;
}
