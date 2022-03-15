package com.cleaning.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CLEANING_DETAILS")
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class CleaningDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String squareMeters;

    @Enumerated(EnumType.STRING)
    private Parking parking;

    @Enumerated(EnumType.STRING)
    private HomeAccess homeAccess;

    @OneToOne(mappedBy = "cleaningDetails")
    private CleaningService cleaningService;
}
