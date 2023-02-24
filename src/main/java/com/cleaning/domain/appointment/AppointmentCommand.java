package com.cleaning.domain.appointment;

import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.users.*;
import lombok.*;

import java.time.*;

@AllArgsConstructor
@Getter
public class AppointmentCommand {

    private final Long id;
    private final Long cleaningServiceId;
    private final Long employeeId;
    private final String cleaningDate;
    private final TimeSlot timeSlot;
    private final String status;

    public Appointment toDomain(CleaningService cleaningService, Employee employee) {

        return new Appointment.AppointmentBuilder()
                .withCleaningService(cleaningService)
                .withEmployee(employee)
                .withCleaningDate(LocalDate.parse(cleaningDate))
                .withTimeSlot(timeSlot)
                .withStatus(AppointmentStatus.valueOf(status))
                .build();
    }
}
