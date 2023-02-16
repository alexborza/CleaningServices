package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.exposition.representation.response.appointment.*;
import com.cleaning.exposition.representation.response.shared.*;
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
    public ResponseEntity<Void> updateJobInformation(@PathVariable Long jobInformationId, @RequestBody JobInformationRepresentation representation){
        employeeService.updateJobInformation(jobInformationId, representation);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/available-intervals")
    public Set<EmployeeAvailableIntervals> getEmployeesAvailableIntervalsForDate(@RequestParam String date, @RequestParam Integer timeEstimation){
        return employeeService.getEmployeesAvailableIntervalsForDate(date, timeEstimation);
    }

    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<AppointmentRepresentation>> getEmployeesAppointmentsForDate(
            @PathVariable Long id,
            @RequestParam String date){

        List<AppointmentRepresentation> appointments = employeeService.getEmployeesAppointmentsForDate(id, date);
        return ResponseEntity.ok(appointments);
    }
}
