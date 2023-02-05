package com.cleaning.exposition.representation.response.cleaning_service;


import com.cleaning.domain.cleaning_service.*;
import com.cleaning.exposition.representation.response.cleaning_service.details.*;
import lombok.*;

import java.util.*;

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
}
