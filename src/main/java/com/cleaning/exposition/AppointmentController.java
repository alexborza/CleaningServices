package com.cleaning.exposition;

import com.cleaning.application.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;


//    @PutMapping("/end-service/{id}")
//    public void endCleaningService(@PathVariable Long id){
//        cleaningServiceService.endCleaningService(id);
//    }
//
//    @PutMapping("/finish-service/{id}")
//    public void finishCleaningService(@PathVariable Long id, @RequestParam String date){
//        cleaningServiceService.finishCleaningService(id, date);
//    }
//
//    @PostMapping("/reschedule/{id}")
//    public void rescheduleCleaningService(@PathVariable Long id, @RequestBody RescheduleDateDto dto) {
//        cleaningServiceService.rescheduleCleaningService(id, dto);
//    }
}
