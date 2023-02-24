package com.cleaning.domain.cleaning_service;

import com.cleaning.domain.cleaning_service.details.*;
import com.cleaning.domain.users.*;
import lombok.*;

import java.util.*;

@NoArgsConstructor
@Getter
public class CleaningServiceBuilder {

    private Long id;
    private ContactInfo contactInfo;
    private Location location;
    private CleaningDetails cleaningDetails;
    private Client client;
    private List<Message> messages = new ArrayList<>();
    private Frequency frequency;
    private Payment payment;
    private CleaningType cleaningType;
    private Double total;
    private Integer timeEstimation;

    public CleaningServiceBuilder withContactInfo(ContactInfo contactInfo) {

        this.contactInfo = contactInfo;
        return this;
    }

    public CleaningServiceBuilder withLocation(Location location) {

        this.location = location;
        return this;
    }

    public CleaningServiceBuilder withCleaningDetails(CleaningDetails cleaningDetails) {

        this.cleaningDetails = cleaningDetails;
        return this;
    }

    public CleaningServiceBuilder withClient(Client client) {

        this.client = client;
        return this;
    }

    public CleaningServiceBuilder withMessages(List<Message> messages) {

        if(messages != null) {
            this.messages.addAll(messages);
        }
        return this;
    }

    public CleaningServiceBuilder withMessage(Message message) {

        this.messages.add(message);
        return this;
    }

    public CleaningServiceBuilder withFrequency(Frequency frequency) {

        this.frequency = frequency;
        return this;
    }

    public CleaningServiceBuilder withPayment(Payment payment) {

        this.payment = payment;
        return this;
    }

    public CleaningServiceBuilder withType(CleaningType cleaningType) {

        this.cleaningType = cleaningType;
        return this;
    }

    public CleaningServiceBuilder withTotal(Double total) {

        this.total = total;
        return this;
    }

    public CleaningServiceBuilder withTimeEstimation(Integer timeEstimation) {

        this.timeEstimation = timeEstimation;
        return this;
    }

    public CleaningServiceBuilder withCleaningService(CleaningService cleaningService) {
        this.id = cleaningService.getId();
        this.contactInfo = cleaningService.getContactInfo();
        this.location = cleaningService.getLocation();
        this.cleaningDetails = cleaningService.getCleaningDetails();
        this.client = cleaningService.getClient();
        this.frequency = cleaningService.getFrequency();
        this.payment = cleaningService.getPayment();
        this.cleaningType = cleaningService.getCleaningType();
        this.total = cleaningService.getTotal();
        this.timeEstimation = cleaningService.getTimeEstimation();
        this.messages = cleaningService.getMessages();
        return this;
    }

    public CleaningService build() {

        return new CleaningService(this);
    }
}
