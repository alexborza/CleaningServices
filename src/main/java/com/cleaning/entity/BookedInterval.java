package com.cleaning.entity;

import lombok.*;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookedInterval {
    private int startingHour;
    private int endingHour;
    private Long cleaningServiceId;
}
