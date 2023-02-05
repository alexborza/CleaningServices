package com.cleaning.exposition.representation.response;

import lombok.*;

@AllArgsConstructor
@Getter
public class JwtResponse {
    private final String token;
    private final String type = "Bearer";
    private final Long id;
    private final String username;
    private final String email;
    private final String role;
}
