package com.cleaning.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Days_in_agenda")
@Getter
@Setter
@NoArgsConstructor
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "booked_intervals", joinColumns = @JoinColumn(name = "day_id"))
    private List<BookedInterval> bookedIntervals = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "available_intervals", joinColumns = @JoinColumn(name = "day_id"))
    private List<AvailableInterval> availableIntervals = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Agenda agenda;
}
