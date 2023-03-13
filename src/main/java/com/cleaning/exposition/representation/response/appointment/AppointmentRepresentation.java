package com.cleaning.exposition.representation.response.appointment;

import com.cleaning.domain.appointment.*;
import lombok.*;

import java.time.*;

@AllArgsConstructor
@Getter
public class AppointmentRepresentation {

    private final Long id;
    private final Long cleaningServiceId;
    private final Long employeeId;
    private final LocalDate cleaningDate;
    private final TimeSlotRepresentation timeSlot;
    private final String status;

    public static AppointmentRepresentation fromDomain(Appointment appointment) {
        return new AppointmentRepresentation(
                appointment.getId(),
                appointment.getCleaningService().getId(),
                appointment.getEmployee().getId(),
                appointment.getCleaningDate(),
                TimeSlotRepresentation.fromDomain(appointment.getTimeSlot()),
                appointment.getStatus().toString()
        );
    }
}
