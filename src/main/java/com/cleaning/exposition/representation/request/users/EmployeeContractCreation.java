package com.cleaning.exposition.representation.request.users;

import com.cleaning.domain.users.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class EmployeeContractCreation {
    private final String username;
    private final String email;
    private final String password;
    private final UserInformationCreation userInformationCreation;
    private final JobInformationCreation jobInformationCreation;

    public Employee toDomain(String encodedPassword) {
        return new Employee.Builder()
                .withUsername(username)
                .withEmail(email)
                .withPassword(encodedPassword)
                .withUserInformation(userInformationCreation.toDomain())
                .withJobInformation(jobInformationCreation.toDomain())
                .build();
    }
}
