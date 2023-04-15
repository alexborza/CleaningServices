package com.cleaning.infrastructure;

import com.cleaning.domain.appointment.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.time.*;

@Service
@Transactional
public class AppointmentProcessService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void processNotCompletedAppointments() {
        LocalDate currentDate = LocalDate.now();
        appointmentRepository.updateNotCompletedDueAppointments(currentDate);
    }
}
