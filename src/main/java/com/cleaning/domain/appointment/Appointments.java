package com.cleaning.domain.appointment;

import java.util.*;

public interface Appointments {

    List<Appointment> findAllByCleaningDate(String date);
}
