package com.cleaning.exposition.representation.response.users;

import com.cleaning.domain.users.*;
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

    @Override
    public User toDomain() {
        return new Employee(
                getUsername(),
                getEmail(),
                getPassword(),
                getUserInformation().toDomain(),
                getEmployeeInformation().toDomain()
        );
    }

    public static EmployeeRepresentation mapFromDomain(Employee employee) {
        return new EmployeeRepresentation(
                employee.getId(),
                employee.getUsername(),
                employee.getEmail(),
                employee.getPassword(),
                UserInformationRepresentation.fromDomain(employee.getUserInformation()),
                EmployeeInformationRepresentation.fromDomain(employee.getEmployeeInformation())
        );
    }
}
