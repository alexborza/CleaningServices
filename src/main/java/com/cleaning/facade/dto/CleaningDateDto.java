package com.cleaning.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CleaningDateDto {
    private final String cleaningDate;
    private final int startingHour;
    private final int finishingHour;
}
