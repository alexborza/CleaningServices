package com.cleaning.controller;

import com.cleaning.facade.OfficeCleaningServiceFacade;
import com.cleaning.facade.dto.OfficeCleaningDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/office-cleaning-service")
public class OfficeCleaningServiceController {

    @Autowired
    private OfficeCleaningServiceFacade facade;

    @PostMapping("/quote-request")
    public void quoteRequestForOfficeCleaning(@RequestBody OfficeCleaningDto officeCleaningDto){
        facade.quoteRequestForOfficeCleaning(officeCleaningDto);
    }
}
