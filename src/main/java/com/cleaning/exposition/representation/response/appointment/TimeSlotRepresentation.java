package com.cleaning.exposition.representation.response.appointment;

import lombok.*;

@Getter
@AllArgsConstructor
public class TimeSlotRepresentation {
    private final int startingHour;
    private final int finishingHour;
}
