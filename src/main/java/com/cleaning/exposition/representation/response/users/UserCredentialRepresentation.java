package com.cleaning.exposition.representation.response.users;

import lombok.*;

@AllArgsConstructor
@Getter
public class UserCredentialRepresentation {
    private final String username;
    private final String email;
}
