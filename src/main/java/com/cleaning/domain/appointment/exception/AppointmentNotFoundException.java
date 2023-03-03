package com.cleaning.domain.appointment.exception;

public class AppointmentNotFoundException extends RuntimeException {

    public AppointmentNotFoundException(Long id) {
        super("Appointment not found for id = " + id.toString());
    }
}
