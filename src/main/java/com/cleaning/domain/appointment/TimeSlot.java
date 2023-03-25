package com.cleaning.domain.appointment;

import com.cleaning.domain.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.*;
import java.util.*;

@Embeddable
@NoArgsConstructor
@Getter
@TimeSlotValidator.ValidConstraint
public class TimeSlot extends BaseEntity implements Comparable<TimeSlot> {

    @NotNull
    @Min(8)
    private Integer startingHour;

    @NotNull
    @Max(17)
    private Integer endingHour;

    public TimeSlot(Integer startingHour, Integer endingHour) {
        this.startingHour = startingHour;
        this.endingHour = endingHour;
        validate(this);
    }

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
