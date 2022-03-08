package com.cleaning.controller;

import com.cleaning.entity.*;
import com.cleaning.facade.OfficeCleaningServiceFacade;
import com.cleaning.facade.dto.OfficeCleaningDto;
import com.cleaning.facade.dto.OfficeCleaningQuoteRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/office-cleaning-service")
public class OfficeCleaningServiceController {

    @Autowired
    private OfficeCleaningServiceFacade facade;

    @PostMapping("/quote-request")
    public void quoteRequestForOfficeCleaning(@RequestParam(required = false) Long userId, @RequestBody OfficeCleaningDto officeCleaningDto){
        facade.quoteRequestForOfficeCleaning(userId, officeCleaningDto);
    }

    @PutMapping("/quote-request/{id}")
    public void updateQuoteRequestForOfficeCleaning(@PathVariable Long id, @RequestBody OfficeCleaningQuoteRequestDto dto){
        facade.updateQuoteRequestForOfficeCleaning(id, dto);
    }

    @PutMapping("/request-status/{id}")
    public void updateRequestStatus(@PathVariable Long id, @RequestParam(name = "status") OfficeCleaningStatus status){
        facade.updateRequestStatus(id, status);
    }

    @GetMapping("/quote-requests")
    public List<OfficeCleaningDto> getQuoteRequests(){
        return facade.getQuoteRequests();
    }

    @GetMapping("/quote-requests/{id}")
    public OfficeCleaningDto getQuoteRequest(@PathVariable Long id){
        return facade.getQuoteRequest(id);
    }
}
