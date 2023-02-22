package com.cleaning.exposition.representation.response.cleaning_service;

import com.cleaning.domain.cleaning_service.*;
import com.cleaning.exposition.representation.response.appointment.*;
import lombok.*;

import java.time.*;

@AllArgsConstructor
@Getter
public class CleaningServiceMinimalRepresentation {

    private final Long id;
    private final CleaningType cleaningType;
    private final Double total;
    private final Integer timeEstimation;
    private final LocalDate nextCleaningDate;
    private final TimeSlotRepresentation timeSlotRepresentation;

    public static CleaningServiceMinimalRepresentation fromDomain(CleaningServiceMinimalView view) {

        return new CleaningServiceMinimalRepresentation(
                view.getId(),
                view.getCleaningType(),
                view.getTotal(),
                view.getTimeEstimation(),
                view.getNextCleaningDate(),
                new TimeSlotRepresentation(view.getStartingHour(), view.getEndingHour())
        );
    }

}
