package com.cleaning.exposition.representation.response.users;

import com.cleaning.domain.users.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@JsonTypeName("employee")
public class EmployeeRepresentation extends UserRepresentation {
    private final JobInformationRepresentation jobInformationRepresentation;

    public EmployeeRepresentation(
            Long id,
            String username,
            String email,
            String password,
            UserInformationRepresentation userInformation,
            JobInformationRepresentation jobInformationRepresentation) {

        super(id, username, email, password, userInformation);
        this.jobInformationRepresentation = jobInformationRepresentation;
    }

    public static EmployeeRepresentation mapFromDomain(Employee employee) {
        return new EmployeeRepresentation(
                employee.getId(),
                employee.getUsername(),
                employee.getEmail(),
                employee.getPassword(),
                UserInformationRepresentation.fromDomain(employee.getUserInformation()),
                JobInformationRepresentation.fromDomain(employee.getJobInformation())
        );
    }
}
