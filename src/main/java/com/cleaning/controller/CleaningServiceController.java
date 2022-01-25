package com.cleaning.controller;

import com.cleaning.facade.CleaningServiceFacade;
import com.cleaning.facade.dto.CleaningServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/booking/cleaning-service")
public class CleaningServiceController {

    @Autowired
    private CleaningServiceFacade cleaningServiceFacade;

    @PostMapping
    public void createCleaningService(@RequestBody CleaningServiceDto cleaningServiceDto){
        cleaningServiceFacade.createCleaningService(cleaningServiceDto);
    }

    @GetMapping("/booked-hours")
    public List<String> getBookedHoursForDate(@RequestParam String date){
        return cleaningServiceFacade.getBookedHoursForDate(date);
    }
}
