package com.cleaning.domain.cleaning_service;

import com.cleaning.domain.cleaning_service.details.*;
import com.cleaning.domain.users.*;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "cleaning_service")
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

    @ElementCollection
    @CollectionTable(name = "messages", joinColumns = @JoinColumn(name = "cleaning_service_id"))
    @OrderColumn
    private List<Message> messages;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @Enumerated(EnumType.STRING)
    private Payment payment;

    @Enumerated(EnumType.STRING)
    private CleaningType cleaningType;

    private Double total;

    private Integer timeEstimation;

    private CleaningService() {

    }

    public CleaningService(CleaningServiceBuilder builder) {
        List<Message> messages = builder.getMessages();

        this.contactInfo = builder.getContactInfo();
        this.location = builder.getLocation();
        this.cleaningDetails = builder.getCleaningDetails();
        this.client = builder.getClient();
        this.frequency = builder.getFrequency();
        this.payment = builder.getPayment();
        this.cleaningType = builder.getCleaningType();
        this.total = builder.getTotal();
        this.timeEstimation = builder.getTimeEstimation();
        this.messages = messages == null ? Collections.emptyList() : messages;
    }
}