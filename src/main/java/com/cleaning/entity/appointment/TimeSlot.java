package com.cleaning.entity.appointment;

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
