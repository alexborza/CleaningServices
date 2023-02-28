package com.cleaning.exposition.representation.response.appointment;

import com.cleaning.domain.appointment.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class AppointmentRepresentation {

    private final Long id;
    private final Long cleaningServiceId;
    private final Long employeeId;
    private final String cleaningDate;
    private final TimeSlotRepresentation timeSlot;
    private final String status;

    public static AppointmentRepresentation fromDomain(Appointment appointment) {
        return new AppointmentRepresentation(
                appointment.getId(),
                appointment.getCleaningService().getId(),
                appointment.getEmployee().getId(),
                appointment.getCleaningDate().toString(),
                TimeSlotRepresentation.fromDomain(appointment.getTimeSlot()),
                appointment.getStatus().toString()
        );
    }

    public AppointmentCommand toCommand() {

        return new AppointmentCommand(
                cleaningDate,
                timeSlot.toDomain(),
                AppointmentStatus.ACTIVE
        );
    }
}
