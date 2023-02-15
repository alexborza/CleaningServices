package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.exposition.representation.response.appointment.*;
import com.cleaning.exposition.representation.response.cleaning_service.*;
import com.cleaning.exposition.representation.response.users.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("job-information/{userId}")
    public void updateJobInformation(@PathVariable Long jobInformationId, @RequestBody JobInformationRepresentation representation){
        employeeService.updateJobInformation(jobInformationId, representation);
    }

//
//    @GetMapping("/employees-day-agenda")
//    public List<EmployeesDayAgenda> getEmployeesAgendaForDate(@RequestParam String date, @RequestParam String frequency){
//        return employeeService.getEmployeesAgendaForDate(date, frequency);
//    }
//
    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<AppointmentRepresentation>> getEmployeesAppointmentsForDate(
            @PathVariable Long id,
            @RequestParam String date){

        List<AppointmentRepresentation> appointments = employeeService.getEmployeesAppointmentsForDate(id, date);
        return ResponseEntity.ok(appointments);
    }
}
