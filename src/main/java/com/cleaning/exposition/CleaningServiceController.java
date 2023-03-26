package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.domain.appointment.*;
import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.cleaning_service.description.*;
import com.cleaning.domain.cleaning_service.prices.*;
import com.cleaning.exposition.representation.request.appointment.*;
import com.cleaning.exposition.representation.request.cleaning_service.*;
import com.cleaning.exposition.representation.response.cleaning_service.*;
import com.cleaning.exposition.representation.response.cleaning_service.description.*;
import com.cleaning.exposition.representation.response.cleaning_service.prices.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.util.stream.Collectors.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/cleaning-service")
public class CleaningServiceController {

    @Autowired
    private CleaningServiceService cleaningServiceService;


    @GetMapping("/client/{userId}")
    public ResponseEntity<List<CleaningServiceMinimalRepresentation>> findClientsCleaningServices(@PathVariable Long userId) {

        List<CleaningServiceMinimalView> cleaningServices = cleaningServiceService.findClientsCleaningServices(userId);

        return ResponseEntity.ok(
                cleaningServices.stream()
                        .map(CleaningServiceMinimalRepresentation::fromDomain)
                        .collect(toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CleaningServiceRepresentation> getCleaningService(@PathVariable Long id) {

        CleaningService cleaningService = cleaningServiceService.getCleaningService(id);
        List<Appointment> cleaningServiceAppointments = cleaningServiceService.findCleaningServiceAppointments(id);

        return ResponseEntity.ok(
                CleaningServiceRepresentation.fromDomain(cleaningService, cleaningServiceAppointments)
        );
    }

    @PostMapping
    public ResponseEntity<Void> createCleaningService(@RequestParam(required = false) Long clientId,
                                                      @RequestBody CleaningServiceCreation cleaningServiceCreation) {

        List<AppointmentCommand> appointmentCommands = cleaningServiceCreation.getAppointments().stream()
                .map(AppointmentCreation::toCommand)
                .collect(toList());

        cleaningServiceService.createCleaningService(clientId, cleaningServiceCreation.toCommand(), appointmentCommands);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/message/{id}")
    public ResponseEntity<Void> addMessageToCleaningService(@PathVariable Long id, @RequestBody MessageCreation messageCreation) {

        Message message = messageCreation.toDomain();
        cleaningServiceService.addMessageToCleaningService(id, message);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/descriptions")
    public ResponseEntity<CleaningDescriptionRepresentation> getCleaningDescriptions() {

        Optional<CleaningDescription> optionalCleaningDescription = cleaningServiceService.getDescriptions();

        return optionalCleaningDescription
                .map(cleaningDescription -> ResponseEntity.ok(CleaningDescriptionRepresentation.fromDomain(cleaningDescription)))
                .orElseGet(() -> ResponseEntity.ok(CleaningDescriptionRepresentation.emptyInstance()));

    }

    @GetMapping("/prices")
    public ResponseEntity<CleaningPricesRepresentation> getCleaningPrices() {
        Optional<CleaningPrice> optionalCleaningPrice = cleaningServiceService.getCleaningServicePrices();

        return optionalCleaningPrice
                .map(cleaningPrice -> ResponseEntity.ok(CleaningPricesRepresentation.fromDomain(cleaningPrice)))
                .orElseGet(() -> ResponseEntity.ok(CleaningPricesRepresentation.emptyInstance()));
    }

    @GetMapping("/messages/{cleaningServiceId}")
    public ResponseEntity<List<MessageRepresentation>> getCleaningServicesMessages(@PathVariable Long cleaningServiceId) {
        List<Message> messages = cleaningServiceService.getCleaningServicesMessages(cleaningServiceId);

        return ResponseEntity.ok(
          messages.stream()
                  .map(MessageRepresentation::fromDomain)
                  .collect(toList())
        );
    }
}
