package com.cleaning.exposition.representation.response.appointment;

import com.cleaning.domain.appointment.*;
import lombok.*;

@Getter
@AllArgsConstructor
public class TimeSlotRepresentation {
    private final int startingHour;
    private final int finishingHour;

    public static TimeSlotRepresentation fromDomain(TimeSlot timeSlot) {
        return new TimeSlotRepresentation(
                timeSlot.getStartingHour(),
                timeSlot.getEndingHour()
        );
    }
}
