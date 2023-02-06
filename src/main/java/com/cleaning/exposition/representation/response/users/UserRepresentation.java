package com.cleaning.exposition.representation.response.users;

import com.cleaning.domain.users.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

@AllArgsConstructor
@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EmployeeRepresentation.class, name = "employee"),
        @JsonSubTypes.Type(value = ClientRepresentation.class, name = "client")
})
public abstract class UserRepresentation {
    private final Long id;
    private final String username;
    private final String email;
    private transient final String password;
    private final UserInformationRepresentation userInformation;

    public abstract User toDomain();

    public static UserRepresentation fromDomain(User user) {
        if(user instanceof Employee)
            return EmployeeRepresentation.mapFromDomain((Employee) user);
        else if(user instanceof Client)
            return ClientRepresentation.mapFromDomain((Client) user);

        throw new IllegalArgumentException("Unknown subtype of UserRepresentation");
    }

    protected UserInformation toDomainUserInformation(UserInformationRepresentation representation) {
        return new UserInformation(
                representation.getFullName(),
                representation.getAddress(),
                representation.getPhoneNumber(),
                representation.getBirthDate()
        );
    }
}