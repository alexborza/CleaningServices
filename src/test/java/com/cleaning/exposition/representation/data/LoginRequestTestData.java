package com.cleaning.exposition.representation.data;

import com.cleaning.exposition.representation.request.users.*;

public class LoginRequestTestData {

    public static LoginRequest dummyLoginRequest(String username, String password) {
        return new LoginRequest(
                username,
                password
        );
    }
}
