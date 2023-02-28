package com.cleaning.application;

import com.cleaning.domain.appointment.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void completeAppointment(Long id) {
        if(!appointmentRepository.existsById(id)) {
            throw new AppointmentNotFoundException(id);
        }

        appointmentRepository.updateStatusCompleted(id);
    }

    public void cancelAppointment(Long id) {
        if(!appointmentRepository.existsById(id)) {
            throw new AppointmentNotFoundException(id);
        }

        appointmentRepository.deleteById(id);
    }

//    public void rescheduleCleaningService(Long id, RescheduleDateDto dto){
//
//    }

}
