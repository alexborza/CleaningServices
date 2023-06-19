package com.cleaning.exposition.representation.request.users;

import lombok.*;

@AllArgsConstructor
@Getter
public class LoginRequest {
    private final String username;
    private final String password;
}
