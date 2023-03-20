package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.domain.appointment.*;
import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.cleaning_service.description.*;
import com.cleaning.domain.cleaning_service.prices.*;
import com.cleaning.domain.users.*;
import com.cleaning.exposition.representation.request.cleaning_service.description.*;
import com.cleaning.exposition.representation.request.cleaning_service.prices.*;
import com.cleaning.exposition.representation.request.users.*;
import com.cleaning.exposition.representation.response.appointment.*;
import com.cleaning.exposition.representation.response.cleaning_service.*;
import com.cleaning.exposition.representation.response.users.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/administrator")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/employee-contract")
    public ResponseEntity<Void> createEmployeeContract(@RequestBody EmployeeContractCreation employeeContractCreation){
        String encodedPassword = encoder.encode(employeeContractCreation.getPassword());
        Employee employee = employeeContractCreation.toDomain(encodedPassword);

        administratorService.createEmployeeContract(employee);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/employees")
    public ResponseEntity<List<UserMinimalRepresentation>> getAllEmployees(){
        List<UserMinimalView> employees = administratorService.getAllEmployees();

        return ResponseEntity.ok(
                employees.stream()
                        .map(UserMinimalRepresentation::fromDomain)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/employees-appointments/{date}")
    public ResponseEntity<List<EmployeeAppointmentRepresentation>> getAllEmployeesAppointmentsByDate(@PathVariable String date){
        Map<UserMinimalView, List<Appointment>> employeesAppointmentsByDate = administratorService.getAllEmployeesAppointmentsByDate(date);

        return ResponseEntity.ok(
                employeesAppointmentsByDate.entrySet().stream()
                        .map(entry -> EmployeeAppointmentRepresentation.fromDomain(entry.getKey(), entry.getValue()))
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/create-descriptions")
    public ResponseEntity<Void> createDescriptions(@RequestBody CleaningDescriptionCreation cleaningDescriptionCreation){
        CleaningDescription cleaningDescription = cleaningDescriptionCreation.toDomain();
        administratorService.createDescriptions(cleaningDescription);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create-prices")
    public ResponseEntity<Void> createCleaningPrices(@RequestBody CleaningPriceCreation cleaningPriceCreation) {
        CleaningPrice cleaningPrice = cleaningPriceCreation.toDomain();
        administratorService.createCleaningPrices(cleaningPrice);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all-cleaning-services")
    public ResponseEntity<List<CleaningServiceMinimalRepresentation>> getAllCleaningServices(){
        List<CleaningServiceMinimalView> cleaningServiceMinimalViews = administratorService.getAllCleaningServices();

        return ResponseEntity.ok(
                cleaningServiceMinimalViews.stream()
                        .map(CleaningServiceMinimalRepresentation::fromDomain)
                        .collect(Collectors.toList())
        );
    }
}
