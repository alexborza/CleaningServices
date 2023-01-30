package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.exposition.representation.response.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/administrator")
public class AdministratorController {

//    @Autowired
//    private AdministratorService administratorService;
//
//    @PostMapping("/employee-contract")
//    public ResponseEntity<MessageResponse> createEmployeeContract(@RequestBody UserDto dto){
//        return administratorService.createEmployeeContract(dto);
//    }
//
//    @GetMapping("/employees")
//    public List<EmployeeDto> getAllEmployees(){
//        return administratorService.getAllEmployees();
//    }
//
//    @GetMapping("/services-agenda")
//    public List<ServicesAgenda> getServicesAgenda(@RequestParam String date){
//        return administratorService.getServicesAgenda(date);
//    }
//
//    @PostMapping("/create-descriptions")
//    public void createDescriptions(@RequestBody CleaningServiceDescriptionDto dto){
//        administratorService.createDescriptions(dto);
//    }
//
//    @PutMapping("/update-descriptions/{id}")
//    public void updateDescriptions(@PathVariable Long id, @RequestBody CleaningServiceDescriptionDto dto){
//        administratorService.updateDescriptions(id, dto);
//    }
//
//    @PostMapping("/create-prices")
//    public void createCleaningPrices(@RequestBody CleaningServicePricesDto dto) {
//        administratorService.createCleaningPrices(dto);
//    }
//
//    @PutMapping("/update-prices/{id}")
//    public void updateCleaningPrices(@PathVariable Long id, @RequestBody CleaningServicePricesDto dto){
//        administratorService.updateCleaningPrices(id, dto);
//    }
}
