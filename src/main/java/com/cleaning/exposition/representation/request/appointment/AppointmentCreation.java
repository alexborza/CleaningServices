package com.cleaning.exposition.representation.request.appointment;

import com.cleaning.domain.appointment.*;
import com.cleaning.exposition.representation.response.appointment.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class AppointmentCreation {
    private final Long employeeId;
    private final String cleaningDate;
    private final TimeSlotRepresentation timeSlot;

    public AppointmentCommand toCommand() {

        return new AppointmentCommand(employeeId, cleaningDate, timeSlot.toDomain(), AppointmentStatus.ACTIVE);
    }
}
