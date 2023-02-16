package com.cleaning.exposition.representation.response.appointment;

import com.cleaning.domain.appointment.*;
import lombok.*;

@Getter
@AllArgsConstructor
public class TimeSlotRepresentation implements Comparable<TimeSlotRepresentation> {
    private final int startingHour;
    private final int finishingHour;

    public static TimeSlotRepresentation fromDomain(TimeSlot timeSlot) {
        return new TimeSlotRepresentation(
                timeSlot.getStartingHour(),
                timeSlot.getEndingHour()
        );
    }

    @Override
    public int compareTo(TimeSlotRepresentation o) {
        return Integer.compare(startingHour, o.startingHour);
    }
}
