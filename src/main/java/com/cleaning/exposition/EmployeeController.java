package com.cleaning.exposition;

import com.cleaning.application.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/employee")
public class EmployeeController {
//
//    @Autowired
//    private EmployeeService employeeService;
//
//    @PostMapping("job-info/{userId}")
//    public void modifyJobInfo(@PathVariable Long userId, @RequestBody JobInformationDto dto){
//        employeeService.modifyJobInfo(userId, dto);
//    }
//
//    @GetMapping("/{id}")
//    public EmployeeDto getEmployee(@PathVariable Long id){
//        return employeeService.getEmployee(id);
//    }
//
//    @GetMapping("/employees-day-agenda")
//    public List<EmployeesDayAgenda> getEmployeesAgendaForDate(@RequestParam String date, @RequestParam String frequency){
//        return employeeService.getEmployeesAgendaForDate(date, frequency);
//    }
//
//    @GetMapping("/employee-agenda/{id}")
//    public List<CleaningServiceDto> getEmployeeCleaningServicesForDate(@PathVariable Long id, @RequestParam String date){
//        return employeeService.getEmployeeCleaningServicesForDate(id, date);
//    }
}
