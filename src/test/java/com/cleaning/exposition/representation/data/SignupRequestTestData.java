package com.cleaning.exposition.representation.data;

import com.cleaning.exposition.representation.request.*;

public class SignupRequestTestData {

    public static SignupRequest dummySignupRequest() {
        return new SignupRequest(
                "username",
                "email",
                "password"
        );
    }
}
