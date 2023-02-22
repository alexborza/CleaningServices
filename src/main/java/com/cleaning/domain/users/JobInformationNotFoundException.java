package com.cleaning.domain.users;

public class JobInformationNotFoundException extends RuntimeException {

    public JobInformationNotFoundException(Long id) {
        super("JobInformation not found for id = " + id.toString());
    }
}
