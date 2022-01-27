package com.cleaning.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "OFFICE_CLEANING")
@Getter
@Setter
@NoArgsConstructor
public class OfficeCleaning {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private SpaceDetails spaceDetails;

    @Embedded
    private SpaceType spaceType;

    @Embedded
    private ContactInfo contactInfo;

    @Embedded
    private Location location;
}
