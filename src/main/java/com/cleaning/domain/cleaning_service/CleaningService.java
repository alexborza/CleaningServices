package com.cleaning.domain.cleaning_service;

import com.cleaning.domain.*;
import com.cleaning.domain.cleaning_service.details.*;
import com.cleaning.domain.users.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Table(name = "cleaning_service")
@Getter
@NoArgsConstructor
public class CleaningService extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Embedded
    private ContactInfo contactInfo;

    @NotNull
    @Embedded
    private Location location;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cleaning_details_id", referencedColumnName = "id")
    private CleaningDetails cleaningDetails;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ElementCollection
    @CollectionTable(name = "messages", joinColumns = @JoinColumn(name = "cleaning_service_id"))
    @OrderColumn
    private List<Message> messages;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Payment payment;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CleaningType cleaningType;

    @NotNull
    private Double total;

    @NotNull
    private Integer timeEstimation;

    public CleaningService(CleaningServiceBuilder builder) {
        List<Message> messages = builder.getMessages();

        this.id = builder.getId();
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
        validate(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleaningService that = (CleaningService) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}