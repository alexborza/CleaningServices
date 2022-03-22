package com.cleaning.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
}
