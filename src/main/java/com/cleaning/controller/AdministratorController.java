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

    @GetMapping("/services-agenda")
    public List<ServicesAgenda> getServicesAgenda(@RequestParam String date){
        return facade.getServicesAgenda(date);
    }

    @GetMapping("/descriptions")
    public CleaningServiceDescriptionDto getDescriptions(){
        return facade.getDescriptions();
    }

    @PostMapping("/create-descriptions")
    public void createDescriptions(@RequestBody CleaningServiceDescriptionDto dto){
        facade.createDescriptions(dto);
    }

    @PutMapping("/update-descriptions/{id}")
    public void updateDescriptions(@PathVariable Long id, @RequestBody CleaningServiceDescriptionDto dto){
        facade.updateDescriptions(id, dto);
    }

    @GetMapping("/prices")
    public CleaningServicePricesDto getCleaningServicePrices(){
        return facade.getCleaningServicePrices();
    }

    @PostMapping("/create-prices")
    public void createCleaningPrices(@RequestBody CleaningServicePricesDto dto) {
        facade.createCleaningPrices(dto);
    }

    @PutMapping("/update-prices/{id}")
    public void updateCleaningPrices(@PathVariable Long id, @RequestBody CleaningServicePricesDto dto){
        facade.updateCleaningPrices(id, dto);
    }
}
