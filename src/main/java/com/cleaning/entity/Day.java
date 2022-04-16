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

    public void deleteBookedInterval(BookedInterval interval){
        this.bookedIntervals.remove(interval);
        calculateAvailableIntervals();
    }

    public static Day create(String date){
        Day day = new Day();
        day.setDate(date);
        day.setAvailableIntervals(new ArrayList<>(List.of(new AvailableInterval(9, 17))));
        return day;
    }

    public static Day dayWithOverlappingIntervals(List<AvailableInterval> availableIntervals, List<BookedInterval> overlappingIntervals){
        Day day = new Day();
        day.setAvailableIntervals(availableIntervals);
        day.setBookedIntervals(overlappingIntervals);
        return day;
    }

    public void calculateAvailableIntervalsForOverlappingIntervals(){
        Set<Integer> hours = getAvailableHours();
        List<Integer> bookedHours = getBookedHours();
        bookedHours.forEach(hours::remove);
        findAvailableIntervals(new ArrayList<>(hours));
    }

    private void calculateAvailableIntervals(){
        ArrayList<Integer> hours = new ArrayList<>(List.of(9,10,11,12,13,14,15,16,17));
        List<Integer> bookedHours = getBookedHours();
        hours.removeAll(bookedHours);
        findAvailableIntervals(hours);
    }

    private Set<Integer> getAvailableHours(){
        Set<Integer> availableHours = new TreeSet<>();
        availableIntervals.forEach(aI -> {
            for(int i = aI.getStartingHour(); i <= aI.getEndingHour(); i++){
                availableHours.add(i);
            }
        });
        return availableHours;
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
            if(i + 1 < hours.size() && (hours.get(i + 1) != hours.get(i) + 1)) {
                // aici as putea face un improvement, sa nu adauge intervale mai mici decat 2 h, interval.size() < 2
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
