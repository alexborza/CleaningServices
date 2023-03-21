package com.cleaning.exposition.representation.response.users;

import com.cleaning.domain.users.*;
import lombok.*;

@AllArgsConstructor
@Getter
public abstract class UserRepresentation {
    private Long id;
    private String username;
    private String email;
    private UserInformationRepresentation userInformation;

    public static UserRepresentation fromDomain(User user) {
        if(user instanceof Employee)
            return EmployeeRepresentation.fromDomain((Employee) user);
        else if(user instanceof Client)
            return ClientRepresentation.fromDomain((Client) user);

        throw new IllegalArgumentException("Unknown subtype of UserRepresentation");
    }
}