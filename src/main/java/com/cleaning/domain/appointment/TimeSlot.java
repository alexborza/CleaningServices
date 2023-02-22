package com.cleaning.domain.appointment;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TimeSlot implements Comparable<TimeSlot> {
    private Integer startingHour;
    private Integer endingHour;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return startingHour.equals(timeSlot.startingHour) && endingHour.equals(timeSlot.endingHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startingHour, endingHour);
    }

    @Override
    public int compareTo(TimeSlot o) {
        return this.getStartingHour().compareTo(o.getStartingHour());
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "startingHour=" + startingHour +
                ", endingHour=" + endingHour +
                '}';
    }
}
