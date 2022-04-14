package com.cleaning.facade.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class DatesToRescheduleDto {
    private final String date;
    private final boolean wasRescheduled;
}
