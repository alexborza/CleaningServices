package com.cleaning.facade.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class RescheduleDateDto {
    private final String dateToReschedule;
    private final String rescheduledDate;
    private final int startingHour;
    private final int endingHour;
}
