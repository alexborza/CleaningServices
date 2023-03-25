package com.cleaning.exposition.representation.response.appointment;

import com.cleaning.domain.appointment.*;
import com.cleaning.utility.*;
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
    private final String hourInterval;
    private final AppointmentStatus status;

    public static AppointmentRepresentation fromDomain(Appointment appointment) {
        return new AppointmentRepresentation(
                appointment.getId(),
                appointment.getCleaningService().getId(),
                appointment.getEmployee().getId(),
                appointment.getCleaningDate(),
                TimeSlotRepresentation.fromDomain(appointment.getTimeSlot()),
                TimeSlotUtility.getTimeSlotInterval(appointment.getTimeSlot().getStartingHour(), appointment.getTimeSlot().getEndingHour()),
                appointment.getStatus()
        );
    }
}
