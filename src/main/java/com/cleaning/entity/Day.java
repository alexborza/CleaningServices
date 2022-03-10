package com.cleaning.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.*;

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

    public void addBookedInterval(BookedInterval interval){
        this.bookedIntervals.add(interval);
        calculateAvailableIntervals();
    }

    private void calculateAvailableIntervals(){
        ArrayList<Integer> hours = new ArrayList<>(List.of(9,10,11,12,13,14,15,16,17));
        List<Integer> bookedHours = getBookedHours();
        hours.removeAll(bookedHours);
        findAvailableIntervals(hours);
    }

    private List<Integer> getBookedHours(){
        List<Integer> bookedHours = new ArrayList<>();
        bookedIntervals.forEach(bI -> {
            for(int i = calculateStartingHourForInterval(bI); i <= calculateEndingHourForInterval(bI); i++){
                bookedHours.add(i);
            }
        });
        return bookedHours;
    }

    private int calculateStartingHourForInterval(BookedInterval bI){
        return bI.getStartingHour() == 9 ? bI.getStartingHour() : bI.getStartingHour() + 1;
    }

    private int calculateEndingHourForInterval(BookedInterval bI){
        return bI.getEndingHour() == 17 ? bI.getEndingHour() : bI.getEndingHour() - 1;
    }

    private void findAvailableIntervals(List<Integer> hours){
        ArrayList<ArrayList<Integer>> intervals = new ArrayList<>();
        ArrayList<Integer> interval = new ArrayList<>();
        for(int i = 0; i < hours.size(); i++) {
            interval.add(hours.get(i));
            if(i + 1 < hours.size()  && (hours.get(i + 1) != hours.get(i) + 1)) {
                intervals.add(interval);
                interval = new ArrayList<>();
            }
        }
        intervals.add(interval);
        this.availableIntervals = intervals.stream()
                .map(intv -> new AvailableInterval(intv.get(0), intv.get(intv.size() - 1)))
                .collect(Collectors.toList());
    }
}
