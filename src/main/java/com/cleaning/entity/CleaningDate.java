package com.cleaning.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Getter
@Setter
public class CleaningDate {
    private String cleaningDate;
    private int startingHour;
    private int finishingHour;
}
