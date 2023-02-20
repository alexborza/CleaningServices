package com.cleaning.exposition.representation.response.appointment;

import com.cleaning.domain.appointment.*;
import lombok.*;

import java.util.*;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlotRepresentation that = (TimeSlotRepresentation) o;
        return startingHour == that.startingHour && finishingHour == that.finishingHour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startingHour, finishingHour);
    }
}
