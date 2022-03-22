package com.cleaning.entity;

import lombok.*;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CleaningDate {
    private String cleaningDate;
    private int startingHour;
    private int finishingHour;
}
