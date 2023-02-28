package com.cleaning.domain.appointment;

public class AppointmentNotFoundException extends RuntimeException {

    public AppointmentNotFoundException(Long id) {
        super("Appointment not found for id = " + id.toString());
    }
}
