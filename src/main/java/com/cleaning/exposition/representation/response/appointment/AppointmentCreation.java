package com.cleaning.exposition.representation.response.appointment;

import lombok.*;

@AllArgsConstructor
@Getter
public class AppointmentCreation {
    private final String cleaningDate;
    private final TimeSlotRepresentation timeSlot;
    private final String status;
}
