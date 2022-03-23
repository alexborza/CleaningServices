package com.cleaning.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.*;
import java.util.*;

@Entity
@Table(name = "CLEANING_SERVICE")
@Getter
@Setter
@NoArgsConstructor
public class CleaningService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private ContactInfo contactInfo;

    @Embedded
    private Location location;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cleaning_details_id", referencedColumnName = "id")
    private CleaningDetails cleaningDetails;

    @Enumerated(EnumType.STRING)
    private CleaningFrequency cleaningFrequency;

    @Embedded
    private CleaningDate cleaningDate;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private double total;

    @Enumerated(EnumType.STRING)
    private CleaningServiceType type;

    private int timeEstimation;

    @ManyToOne(fetch = FetchType.LAZY)
    private User client;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private CleaningStatus status;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "dates_of_cleaning", joinColumns = @JoinColumn(name = "cleaning_service_id"))
    private List<CleaningDate> datesOfCleaning = new ArrayList<>();

    public String getEmployeeName(){
        return employee.getUserInformation().getFullName();
    }

    public void addDateOfCleaning(CleaningDate futureCleaningDate){
        this.datesOfCleaning.add(futureCleaningDate);
    }

    public CleaningDate getNextCleaningDate(){
        if(status == CleaningStatus.Deleted){
            return null;
        }
        if(datesOfCleaning.isEmpty())
            return cleaningDate;
        return getCleaningDateForFrequency();
    }

    private CleaningDate getCleaningDateForFrequency(){
        switch(cleaningFrequency){
            case Weekly:
                return this.nextCleaningDate(7);
            case BiWeekly:
                return this.nextCleaningDate(14);
            case Monthly:
                return this.nextCleaningDate(28);
            default:
                return null;
        }
    }

    private CleaningDate nextCleaningDate(long daysToAdd){
        CleaningDate lastCleaningDate = datesOfCleaning.get(datesOfCleaning.size() - 1);
        LocalDate date = LocalDate.parse(lastCleaningDate.getCleaningDate());
        lastCleaningDate.setCleaningDate(date.plusDays(daysToAdd).toString());
        return lastCleaningDate;
    }
}
