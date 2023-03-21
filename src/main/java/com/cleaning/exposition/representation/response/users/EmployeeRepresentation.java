package com.cleaning.exposition.representation.response.users;

import com.cleaning.domain.users.*;
import lombok.*;

@Getter
public class EmployeeRepresentation extends UserRepresentation {
    private final JobInformationRepresentation jobInformationRepresentation;

    public EmployeeRepresentation(
            Long id,
            String username,
            String email,
            UserInformationRepresentation userInformation,
            JobInformationRepresentation jobInformationRepresentation) {

        super(id, username, email, userInformation);
        this.jobInformationRepresentation = jobInformationRepresentation;
    }

    public static EmployeeRepresentation fromDomain(Employee employee) {
        return new EmployeeRepresentation(
                employee.getId(),
                employee.getUsername(),
                employee.getEmail(),
                UserInformationRepresentation.fromDomain(employee.getUserInformation()),
                JobInformationRepresentation.fromDomain(employee.getJobInformation())
        );
    }
}
