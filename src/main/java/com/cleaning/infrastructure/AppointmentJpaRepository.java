package com.cleaning.infrastructure;

import com.cleaning.domain.appointment.*;
import org.springframework.data.jpa.repository.*;

import java.time.*;
import java.util.*;

public interface AppointmentJpaRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByCleaningDate(LocalDate date);
}
