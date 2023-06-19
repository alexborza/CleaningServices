package com.cleaning.domain.appointment;

import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.users.*;
import lombok.*;

import java.time.*;
import java.util.*;

@AllArgsConstructor
@Getter
public class AppointmentCommand {

    private final Long employeeId;
    private final String cleaningDate;
    private final TimeSlot timeSlot;
    private final AppointmentStatus status;

    public Appointment toDomain(CleaningService cleaningService, Employee employee) {

        return new Appointment.AppointmentBuilder()
                .withCleaningService(cleaningService)
                .withEmployee(employee)
                .withCleaningDate(LocalDate.parse(cleaningDate))
                .withTimeSlot(timeSlot)
                .withStatus(status)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentCommand that = (AppointmentCommand) o;
        return Objects.equals(employeeId, that.employeeId) && Objects.equals(cleaningDate, that.cleaningDate) && Objects.equals(timeSlot, that.timeSlot) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, cleaningDate, timeSlot, status);
    }
}
