package com.cleaning.controller;

import com.cleaning.facade.CleaningServiceFacade;
import com.cleaning.facade.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/booking/cleaning-service")
public class CleaningServiceController {

    @Autowired
    private CleaningServiceFacade cleaningServiceFacade;

    @PostMapping
    public void createCleaningService(@RequestParam Long employeeId, @RequestParam(required = false) Long userId, @RequestBody CleaningServiceDto cleaningServiceDto){
        cleaningServiceFacade.createCleaningService(employeeId, userId, cleaningServiceDto);
    }

    @GetMapping
    public List<CleaningServiceDisplay> getCleaningServices(){
        return cleaningServiceFacade.getCleaningServices();
    }

    @GetMapping("/{id}")
    public CleaningServiceDto getCleaningService(@PathVariable Long id){
        return cleaningServiceFacade.getCleaningService(id);
    }

    @PutMapping("/end-service/{id}")
    public void endCleaningService(@PathVariable Long id){
        cleaningServiceFacade.endCleaningService(id);
    }

    @PutMapping("/finish-service/{id}")
    public void finishCleaningService(@PathVariable Long id, @RequestParam String date){
        cleaningServiceFacade.finishCleaningService(id, date);
    }

    @GetMapping("/dates-of-cleaning/{id}")
    public List<CleaningDateDto> getDatesOfCleaningForCleaningService(@PathVariable Long id){
        return cleaningServiceFacade.getDatesOfCleaningForCleaningService(id);
    }

    @PostMapping("/message/{id}")
    public void addMessageToCleaningService(@PathVariable Long id, @RequestBody MessageDto dto){
        cleaningServiceFacade.addMessageToCleaningService(id, dto);
    }

    @GetMapping("/messages/{id}")
    public List<MessageDto> getMessagesForCleaningService(@PathVariable Long id){
        return cleaningServiceFacade.getMessagesForCleaningService(id);
    }

    @GetMapping("/next-cleaning-date/{id}")
    public CleaningDateDto getNextCleaningDate(@PathVariable Long id){
        return cleaningServiceFacade.getNextCleaningDate(id);
    }

    @GetMapping("/descriptions")
    public CleaningServiceDescriptionDto getDescriptions(){
        return cleaningServiceFacade.getDescriptions();
    }

    @GetMapping("/prices")
    public CleaningServicePricesDto getCleaningServicePrices(){
        return cleaningServiceFacade.getCleaningServicePrices();
    }

    @GetMapping("/dates-to-reschedule/{id}")
    public List<DatesToRescheduleDto> getDatesToReschedule(@PathVariable Long id){
        return cleaningServiceFacade.getDatesToReschedule(id);
    }

    @PostMapping("/reschedule/{id}")
    public void rescheduleCleaningService(@PathVariable Long id, @RequestBody RescheduleDateDto dto) {
        cleaningServiceFacade.rescheduleCleaningService(id, dto);
    }
}
