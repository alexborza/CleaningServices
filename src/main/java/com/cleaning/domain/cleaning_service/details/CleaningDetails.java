package com.cleaning.domain.cleaning_service.details;

import com.cleaning.domain.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "cleaning_details")
@NoArgsConstructor
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class CleaningDetails extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String squareMeters;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Parking parking;

    @Enumerated(EnumType.STRING)
    @NotNull
    private HomeAccess homeAccess;

    public CleaningDetails(String squareMeters, Parking parking, HomeAccess homeAccess) {
        this.squareMeters = squareMeters;
        this.parking = parking;
        this.homeAccess = homeAccess;
    }
}
