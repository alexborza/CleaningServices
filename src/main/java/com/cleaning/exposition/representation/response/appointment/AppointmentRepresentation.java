package com.cleaning.exposition.representation.response.appointment;

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
}
