package com.cleaning.infrastructure.shared;

import com.cleaning.exposition.representation.response.users.*;

public class JwtResponseTestData {

    public static JwtResponse dummyJwtResponse() {
        return new JwtResponse(
                "token",
                1L,
                "username",
                "email",
                "USER"
        );
    }
}
