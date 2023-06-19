package com.cleaning.domain.cleaning_service.exception;

public class CleaningServiceNotFoundException extends RuntimeException {

    public CleaningServiceNotFoundException(Long id) {
        super("CleaningService not found for id = " + id.toString());
    }
}
