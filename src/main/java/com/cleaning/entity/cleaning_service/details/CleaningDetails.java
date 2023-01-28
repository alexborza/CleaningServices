package com.cleaning.entity.cleaning_service.details;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cleaning_details")
@NoArgsConstructor
@Getter
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

    public CleaningDetails(String squareMeters, Parking parking, HomeAccess homeAccess) {
        this.squareMeters = squareMeters;
        this.parking = parking;
        this.homeAccess = homeAccess;
    }
}
