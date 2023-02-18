package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.domain.appointment.*;
import com.cleaning.domain.users.*;
import com.cleaning.exposition.representation.response.appointment.*;
import com.cleaning.exposition.representation.response.shared.*;
import com.cleaning.exposition.representation.response.users.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("job-information/{userId}")
    public ResponseEntity<Void> updateJobInformation(@PathVariable Long jobInformationId, @RequestBody JobInformationRepresentation representation){
        JobInformation jobInformation = representation.toDomain();
        employeeService.updateJobInformation(jobInformationId, jobInformation);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/available-intervals")
    public Set<EmployeeAvailableInterval> getEmployeesAvailableIntervalsForDate(@RequestParam String date, @RequestParam Integer timeEstimation){
        Map<Long, Set<TimeSlot>> employeesAvailableIntervals = employeeService.getEmployeesAvailableIntervalsForDate(date, timeEstimation);

        Set<EmployeeAvailableInterval> availableIntervals = new TreeSet<>();

        employeesAvailableIntervals.forEach((employeeId, timeSlots) -> {
            List<EmployeeAvailableInterval> employeeAvailableIntervals = timeSlots.stream()
                    .map(timeSlot -> new EmployeeAvailableInterval(employeeId, TimeSlotRepresentation.fromDomain(timeSlot)))
                    .collect(Collectors.toList());
            availableIntervals.addAll(employeeAvailableIntervals);
        });

        return availableIntervals;
    }

    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<AppointmentRepresentation>> getEmployeesAppointmentsForDate(
            @PathVariable Long id,
            @RequestParam String date){

        List<Appointment> appointments = employeeService.getEmployeesAppointmentsForDate(id, date);

        return ResponseEntity.ok(
                appointments.stream()
                        .map(AppointmentRepresentation::fromDomain)
                        .collect(Collectors.toList())
        );
    }
}
