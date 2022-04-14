package com.cleaning.entity;

import lombok.*;

import javax.persistence.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RescheduleDate {
    private String dateToReschedule;
    private String rescheduledDate;
    private int startingHour;
    private int endingHour;
}
