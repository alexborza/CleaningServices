package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.exposition.representation.response.*;
import com.cleaning.exposition.representation.response.appointment.*;
import com.cleaning.exposition.representation.response.cleaning_service.description.*;
import com.cleaning.exposition.representation.response.cleaning_service.prices.*;
import com.cleaning.exposition.representation.response.users.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/administrator")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @PostMapping("/employee-contract")
    public ResponseEntity<Void> createEmployeeContract(@RequestBody UserRepresentation representation){
        administratorService.createEmployeeContract(representation);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/employees")
    public ResponseEntity<List<UserRepresentation>> getAllEmployees(){
        List<UserRepresentation> employees = administratorService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/services-agenda/{date}")
    public ResponseEntity<List<AppointmentRepresentation>> getAppointmentsByDate(@PathVariable String date){
        List<AppointmentRepresentation> appointments = administratorService.getAppointmentsByDate(date);
        return ResponseEntity.ok(appointments);
    }

    @PostMapping("/create-descriptions")
    public ResponseEntity<Void> createDescriptions(@RequestBody CleaningDescriptionRepresentation representation){
        administratorService.createDescriptions(representation);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create-prices")
    public ResponseEntity<Void> createCleaningPrices(@RequestBody CleaningPricesRepresentation representation) {
        administratorService.createCleaningPrices(representation);
        return ResponseEntity.ok().build();
    }
}
