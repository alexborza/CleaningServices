package com.cleaning.exposition.representation.response.appointment;

import com.cleaning.domain.appointment.*;
import lombok.*;

import java.util.*;

@Getter
@AllArgsConstructor
public class TimeSlotRepresentation implements Comparable<TimeSlotRepresentation> {
    private final Integer startingHour;
    private final Integer finishingHour;

    public static TimeSlotRepresentation fromDomain(TimeSlot timeSlot) {
        return new TimeSlotRepresentation(
                timeSlot.getStartingHour(),
                timeSlot.getEndingHour()
        );
    }

    @Override
    public int compareTo(TimeSlotRepresentation o) {
        return startingHour.compareTo(o.startingHour);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlotRepresentation that = (TimeSlotRepresentation) o;
        return Objects.equals(startingHour, that.startingHour) && Objects.equals(finishingHour, that.finishingHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startingHour, finishingHour);
    }
}
