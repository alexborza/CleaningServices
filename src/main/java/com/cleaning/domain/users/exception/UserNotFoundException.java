package com.cleaning.domain.users.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("User not found for id = " + id.toString());
    }
}
