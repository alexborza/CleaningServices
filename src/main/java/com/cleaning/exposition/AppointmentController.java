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

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelAppointment(@PathVariable Long id){
        appointmentService.cancelAppointment(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/add/{cleaningServiceId}/{employeeId}")
    public ResponseEntity<Void> addAppointment(@PathVariable Long cleaningServiceId,
                               @PathVariable Long employeeId,
                               @RequestBody AppointmentCreation appointmentCreation) {

        AppointmentCommand appointmentCommand = appointmentCreation.toCommand();
        appointmentService.addAppointment(cleaningServiceId, employeeId, appointmentCommand);

        return ResponseEntity.ok().build();
    }
}
