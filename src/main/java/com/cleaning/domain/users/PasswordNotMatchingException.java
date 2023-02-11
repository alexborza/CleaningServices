package com.cleaning.domain.users;

public class PasswordNotMatchingException extends RuntimeException {

    public PasswordNotMatchingException() {
    }

    public PasswordNotMatchingException(String message) {
        super(message);
    }
}
