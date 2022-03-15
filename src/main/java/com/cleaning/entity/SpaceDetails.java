package com.cleaning.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter
@Setter
public class SpaceDetails {
    private String totalSquareMeters;
    private String numberOfPersons;

    @Enumerated(EnumType.STRING)
    private PrimaryUseOfSpace primaryUseOfSpace;

    @Enumerated(EnumType.STRING)
    private OfficeCleaningFrequency frequencyOfCleaning;

    @Enumerated(EnumType.STRING)
    private DayTimeOfficeCleaning dayTime;
}
