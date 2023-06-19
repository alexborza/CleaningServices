package com.cleaning.exposition.representation.data;

import com.cleaning.exposition.representation.request.users.*;

public class SignupRequestTestData {

    public static SignupRequest dummySignupRequest() {
        return new SignupRequest(
                "username",
                "email",
                "password"
        );
    }

    public static SignupRequest dummySignupRequest(String username, String email) {
        return new SignupRequest(
                username,
                email,
                "password"
        );
    }
}
