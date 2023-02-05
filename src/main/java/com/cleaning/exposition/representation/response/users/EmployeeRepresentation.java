package com.cleaning.exposition.representation.response.users;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@JsonTypeName("employee")
public class EmployeeRepresentation extends UserRepresentation {
    private final EmployeeInformationRepresentation employeeInformation;

    public EmployeeRepresentation(
            Long id,
            String username,
            String email,
            String password,
            UserInformationRepresentation userInformation,
            EmployeeInformationRepresentation employeeInformation) {

        super(id, username, email, password, userInformation);
        this.employeeInformation = employeeInformation;
    }
}
