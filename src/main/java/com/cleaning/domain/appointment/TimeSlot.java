package com.cleaning.domain.appointment;

import lombok.*;

import javax.persistence.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TimeSlot {
    private Integer startingHour;
    private Integer endingHour;
}
