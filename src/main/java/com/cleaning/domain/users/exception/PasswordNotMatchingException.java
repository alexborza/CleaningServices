package com.cleaning.domain.users.exception;

public class PasswordNotMatchingException extends RuntimeException {

    public PasswordNotMatchingException(String message) {
        super(message);
    }
}
