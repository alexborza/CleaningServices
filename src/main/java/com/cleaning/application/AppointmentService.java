package com.cleaning.application;

import com.cleaning.domain.appointment.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

//    public void endCleaningService(Long id){
//
//    }
//
//    public void finishCleaningService(Long id, String date) {
//
//    }
//
//    public void rescheduleCleaningService(Long id, RescheduleDateDto dto){
//
//    }
}
