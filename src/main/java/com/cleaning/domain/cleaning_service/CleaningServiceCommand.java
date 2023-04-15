package com.cleaning.domain.cleaning_service;

import com.cleaning.domain.cleaning_service.details.*;
import com.cleaning.domain.users.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class CleaningServiceCommand {
    private final ContactInfo contactInfo;
    private final Location location;
    private final CleaningDetails cleaningDetails;
    private final Frequency frequency;
    private final Payment payment;
    private final double total;
    private final int timeEstimation;
    private final CleaningType type;

    public CleaningService toDomain(Client client) {

        return new CleaningServiceBuilder()
                .withContactInfo(contactInfo)
                .withLocation(location)
                .withCleaningDetails(cleaningDetails)
                .withClient(client)
                .withFrequency(frequency)
                .withPayment(payment)
                .withTotal(total)
                .withTimeEstimation(timeEstimation)
                .withType(type)
                .build();
    }


}
