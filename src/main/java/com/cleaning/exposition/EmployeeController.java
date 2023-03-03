package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.domain.appointment.*;
import com.cleaning.domain.users.job_information.*;
import com.cleaning.exposition.representation.request.users.*;
import com.cleaning.exposition.representation.response.appointment.*;
import com.cleaning.exposition.representation.response.shared.*;
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
    public ResponseEntity<Void> updateJobInformation(@PathVariable Long jobInformationId,
                                                     @RequestBody JobInformationCreation jobInformationCreation){

        JobInformation jobInformation = jobInformationCreation.toDomain();
        employeeService.updateJobInformation(jobInformationId, jobInformation);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<AppointmentRepresentation>> getEmployeesAppointmentsForDate(
            @PathVariable Long id,
            @RequestParam String date){

        List<Appointment> appointments = employeeService.getEmployeeAppointmentsForDate(id, date);

        return ResponseEntity.ok(
                appointments.stream()
                        .map(AppointmentRepresentation::fromDomain)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/available-intervals")
    public ResponseEntity<Set<EmployeeAvailableInterval>> getEmployeesAvailableIntervalsForDate(@RequestParam String date, @RequestParam Integer timeEstimation){
        Map<Long, Set<TimeSlot>> employeesAvailableIntervals = employeeService.getEmployeesAvailableIntervalsForDate(date, timeEstimation);

        Set<EmployeeAvailableInterval> availableIntervals = new TreeSet<>();

        employeesAvailableIntervals.forEach((employeeId, timeSlots) ->
            timeSlots.stream()
                    .map(timeSlot -> createEmployeeAvailableInterval(employeeId, timeSlot))
                    .forEach(availableIntervals::add)
        );

        return ResponseEntity.ok(availableIntervals);
    }

    private EmployeeAvailableInterval createEmployeeAvailableInterval(Long employeeId, TimeSlot timeSlot) {
        List<Integer> rangeInterval = IntStream.range(timeSlot.getStartingHour(), timeSlot.getEndingHour() + 1)
                .boxed()
                .collect(Collectors.toList());
        boolean includedLunchBreak = rangeInterval.containsAll(List.of(12, 13));

        return new EmployeeAvailableInterval(
                employeeId,
                TimeSlotRepresentation.fromDomain(timeSlot),
                includedLunchBreak);
    }
}
