package com.cleaning.domain.appointment;

import com.cleaning.domain.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TimeSlotTest {

    @Test
    void should_create_time_slot() {
        TimeSlot timeSlot = new TimeSlot(8, 17);

        assertThat(timeSlot).isNotNull();
        assertThat(timeSlot.getStartingHour()).isEqualTo(8);
        assertThat(timeSlot.getEndingHour()).isEqualTo(17);
    }

    @Test
    void should_not_create_time_slot_when_starting_hour_greater_than_ending_hour() {

        assertThrows(DomainConstraintViolationException.class,
                () -> new TimeSlot(10, 8));
    }

    @Test
    void should_not_create_time_slot_when_starting_hour_out_of_range() {

        assertThrows(DomainConstraintViolationException.class,
                () -> new TimeSlot(7, 10));
    }

    @Test
    void should_not_create_time_slot_when_ending_hour_out_of_range() {

        assertThrows(DomainConstraintViolationException.class,
                () -> new TimeSlot(13, 18));
    }
}
