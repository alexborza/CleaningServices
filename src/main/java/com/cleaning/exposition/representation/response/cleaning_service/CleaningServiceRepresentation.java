package com.cleaning.exposition.representation.response.cleaning_service;


import com.cleaning.domain.appointment.*;
import com.cleaning.domain.cleaning_service.*;
import com.cleaning.exposition.representation.response.appointment.*;
import com.cleaning.exposition.representation.response.cleaning_service.details.*;
import lombok.*;

import java.util.*;

import static java.util.stream.Collectors.*;

@AllArgsConstructor
@Getter
public class CleaningServiceRepresentation {
    private final Long id;
    private final ContactInfoRepresentation contactInfo;
    private final LocationRepresentation location;
    private final CleaningDetailsRepresentation cleaningDetails;
    private final Frequency frequency;
    private final Payment payment;
    private final double total;
    private final int timeEstimation;
    private final CleaningType type;
    private final List<MessageRepresentation> messages;
    private final List<AppointmentRepresentation> appointments;

    public static CleaningServiceRepresentation fromDomain(CleaningService cleaningService, List<Appointment> appointments) {
        List<MessageRepresentation> messageRepresentations = cleaningService.getMessages().stream()
                .map(MessageRepresentation::fromDomain)
                .collect(toList());

        List<AppointmentRepresentation> appointmentRepresentations = appointments.stream()
                .map(AppointmentRepresentation::fromDomain)
                .collect(toList());

        return new CleaningServiceRepresentation(
                cleaningService.getId(),
                ContactInfoRepresentation.fromDomain(cleaningService.getContactInfo()),
                LocationRepresentation.fromDomain(cleaningService.getLocation()),
                CleaningDetailsRepresentation.fromDomain(cleaningService.getCleaningDetails()),
                cleaningService.getFrequency(),
                cleaningService.getPayment(),
                cleaningService.getTotal(),
                cleaningService.getTimeEstimation(),
                cleaningService.getCleaningType(),
                messageRepresentations,
                appointmentRepresentations
        );
    }
}
