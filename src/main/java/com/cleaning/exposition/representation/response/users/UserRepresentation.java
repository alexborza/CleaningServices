package com.cleaning.exposition.representation.response.users;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@AllArgsConstructor
@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EmployeeRepresentation.class, name = "employee"),
})
public class UserRepresentation {
    private final Long id;
    private final String username;
    private final String email;
    private final String password;
    private final UserInformationRepresentation userInformation;
}