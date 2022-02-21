package com.cleaning.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

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

    @Enumerated(EnumType.STRING)
    private OfficeCleaningStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quote_request_id")
    private OfficeCleaningQuoteRequest quoteRequest;

    @ManyToOne
    private User client;

    @ManyToMany(mappedBy = "officeCleanings")
    Set<Employee> employees;
}
