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
    public void createEmployeeContract(@RequestBody UserRepresentation representation){
        administratorService.createEmployeeContract(representation);
    }


    @GetMapping("/employees")
    public List<UserRepresentation> getAllEmployees(){
        return administratorService.getAllEmployees();
    }

    @GetMapping("/services-agenda/{date}")
    public List<AppointmentRepresentation> getAppointmentsByDate(@PathVariable String date){
        return administratorService.getAppointmentsByDate(date);
    }

    @PostMapping("/create-descriptions")
    public void createDescriptions(@RequestBody CleaningDescriptionRepresentation representation){
        administratorService.createDescriptions(representation);
    }

    @PostMapping("/create-prices")
    public void createCleaningPrices(@RequestBody CleaningPricesRepresentation representation) {
        administratorService.createCleaningPrices(representation);
    }
}
