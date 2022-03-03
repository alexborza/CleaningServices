package com.cleaning.controller;

import com.cleaning.facade.*;
import com.cleaning.facade.dto.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

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
}
