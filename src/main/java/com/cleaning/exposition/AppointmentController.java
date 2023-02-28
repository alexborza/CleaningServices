package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.exposition.representation.response.appointment.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PutMapping("/complete/{id}")
    public ResponseEntity<Void> completeAppointment(@PathVariable Long id){
        appointmentService.completeAppointment(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/cancel/{id}")
    public void cancelAppointment(@PathVariable Long id){
        appointmentService.cancelAppointment(id);
    }

//    @PostMapping("/reschedule/{id}")
//    public void rescheduleAppointment(@PathVariable Long id, @RequestBody AppointmentCreation appointmentCreation) {
//        appointmentService.rescheduleAppointment(id, dto);
//    }
}
