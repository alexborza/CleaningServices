package com.cleaning.domain.users.exception;

public class JobInformationNotFoundException extends RuntimeException {

    public JobInformationNotFoundException(Long id) {
        super("JobInformation not found for id = " + id.toString());
    }
}
