package com.cleaning.infrastructure;

import com.cleaning.domain.appointment.*;
import org.springframework.data.jpa.repository.*;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
