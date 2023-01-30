package com.cleaning.domain.cleaning_service;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.cleaning_service.details.*;
import com.cleaning.domain.users.*;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "cleaning_service")
@NoArgsConstructor
@Getter
public class CleaningService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private ContactInfo contactInfo;

    @Embedded
    private Location location;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cleaning_details_id", referencedColumnName = "id")
    private CleaningDetails cleaningDetails;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @OneToMany(mappedBy = "cleaningService")
    private List<Appointment> appointments;

    @ElementCollection
    @CollectionTable(name = "messages", joinColumns = @JoinColumn(name = "cleaning_service_id"))
    private List<Message> messages;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @Enumerated(EnumType.STRING)
    private Payment paymentMethod;

    @Enumerated(EnumType.STRING)
    private CleaningType type;

    private Double total;

    private Integer timeEstimation;

    public CleaningService(ContactInfo contactInfo, Location location, CleaningDetails cleaningDetails, User client, List<Appointment> appointments, List<Message> messages, Frequency frequency, Payment paymentMethod, CleaningType type, Double total, Integer timeEstimation) {
        this.contactInfo = contactInfo;
        this.location = location;
        this.cleaningDetails = cleaningDetails;
        this.client = client;
        this.appointments = appointments;
        this.messages = messages;
        this.frequency = frequency;
        this.paymentMethod = paymentMethod;
        this.type = type;
        this.total = total;
        this.timeEstimation = timeEstimation;
    }
}