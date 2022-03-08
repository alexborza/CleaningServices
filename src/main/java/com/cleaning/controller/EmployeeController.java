package com.cleaning.controller;

import com.cleaning.entity.*;
import com.cleaning.facade.*;
import com.cleaning.facade.dto.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeFacade facade;

    @PostMapping("emergency-contact-info/{userId}")
    public void modifyEmergencyContactInfo(@PathVariable Long userId, @RequestBody EmergencyContactInformationDto dto){
        facade.modifyEmergencyContactInfo(userId, dto);
    }

    @PostMapping("job-info/{userId}")
    public void modifyJobInfo(@PathVariable Long userId, @RequestBody JobInformationDto dto){
        facade.modifyJobInfo(userId, dto);
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmployee(@PathVariable Long id){
        return facade.getEmployee(id);
    }

    @GetMapping("/total-employees")
    public long getTotalNumberOfEmployees(){
        return facade.getTotalNumberOfEmployees();
    }

    @GetMapping("/employees-day-agenda")
    public List<EmployeesDayAgenda> getEmployeesAgendaForDate(@RequestParam String date){
        return facade.getEmployeesAgendaForDate(date);
    }
}
