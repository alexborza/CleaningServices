package com.cleaning.exposition.representation.request.cleaning_service;

import com.cleaning.domain.cleaning_service.*;
import com.cleaning.exposition.representation.request.appointment.*;
import com.cleaning.exposition.representation.request.cleaning_service.details.*;
import lombok.*;

import java.util.*;

@AllArgsConstructor
@Getter
public class CleaningServiceCreation {
    private final ContactInfoCreation contactInfo;
    private final LocationCreation location;
    private final CleaningDetailsCreation cleaningDetails;
    private final Frequency frequency;
    private final Payment payment;
    private final double total;
    private final int timeEstimation;
    private final CleaningType type;
    private final List<AppointmentCreation> appointments;

    public CleaningServiceCommand toCommand() {

        return new CleaningServiceCommand(
                contactInfo.toDomain(),
                location.toDomain(),
                cleaningDetails.toDomain(),
                frequency,
                payment,
                total,
                timeEstimation,
                type
        );
    }
}
