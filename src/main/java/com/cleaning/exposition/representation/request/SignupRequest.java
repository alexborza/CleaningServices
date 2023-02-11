package com.cleaning.exposition.representation.request;

import lombok.*;

@AllArgsConstructor
@Getter
public class SignupRequest {
    private final String username;
    private final String email;
    private final String password;
}
