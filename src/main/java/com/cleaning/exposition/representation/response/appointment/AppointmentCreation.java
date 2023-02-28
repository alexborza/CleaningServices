package com.cleaning.exposition.representation.response.appointment;

import com.cleaning.domain.appointment.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class AppointmentCreation {
    private final String cleaningDate;
    private final TimeSlotRepresentation timeSlot;

    public AppointmentCommand toCommand() {

        return new AppointmentCommand(cleaningDate, timeSlot.toDomain(), AppointmentStatus.ACTIVE);
    }
}
