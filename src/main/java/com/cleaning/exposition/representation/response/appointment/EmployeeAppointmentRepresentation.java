package com.cleaning.exposition.representation.response.appointment;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.users.*;
import lombok.*;

import java.util.*;
import java.util.stream.*;

@AllArgsConstructor
@Getter
public class EmployeeAppointmentRepresentation {

    private final Long employeeId;
    private final String employeeFullName;
    private final List<AppointmentRepresentation> appointmentRepresentations;

    public static EmployeeAppointmentRepresentation fromDomain(UserMinimalView userMinimalView, List<Appointment> appointments) {

        List<AppointmentRepresentation> appointmentRepresentations = appointments.stream()
                .map(AppointmentRepresentation::fromDomain)
                .collect(Collectors.toList());

        return new EmployeeAppointmentRepresentation(
                userMinimalView.getId(),
                userMinimalView.getFullName(),
                appointmentRepresentations
        );
    }
}
