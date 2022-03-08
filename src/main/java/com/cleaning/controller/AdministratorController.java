package com.cleaning.controller;

import com.cleaning.facade.*;
import com.cleaning.facade.dto.*;
import com.cleaning.facade.dto.response.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/administrator")
public class AdministratorController {

    @Autowired
    private AdministratorFacade facade;

    @PostMapping("/employee-contract")
    public ResponseEntity<MessageResponse> createEmployeeContract(@RequestBody UserDto dto){
        return facade.createEmployeeContract(dto);
    }

    @GetMapping("/employees")
    public List<EmployeeDto> getAllEmployees(){
        return facade.getAllEmployees();
    }
}
