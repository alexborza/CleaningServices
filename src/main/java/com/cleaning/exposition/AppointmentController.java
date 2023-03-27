package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.domain.appointment.*;
import com.cleaning.exposition.representation.request.appointment.*;
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

    @PutMapping("/reschedule/{id}")
    public ResponseEntity<Void> rescheduleAppointment(@PathVariable Long id,
                                                      @PathVariable Long cleaningServiceId,
                                                      @RequestBody AppointmentCreation appointmentCreation) {
        appointmentService.rescheduleAppointment(id, cleaningServiceId, appointmentCreation.toCommand());

        return ResponseEntity.ok().build();
    }
}
