package com.cleaning.exposition;

import com.cleaning.application.CleaningServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/booking/cleaning-service")
public class CleaningServiceController {
//
//    @Autowired
//    private CleaningServiceService cleaningServiceService;
//
//    @PostMapping
//    public void createCleaningService(@RequestParam Long employeeId, @RequestParam(required = false) Long userId, @RequestBody CleaningServiceDto cleaningServiceDto){
//        cleaningServiceService.createCleaningService(employeeId, userId, cleaningServiceDto);
//    }
//
//    @GetMapping
//    public List<CleaningServiceDisplay> getCleaningServices(){
//        return cleaningServiceService.getCleaningServices();
//    }
//
//    @GetMapping("/{id}")
//    public CleaningServiceDto getCleaningService(@PathVariable Long id){
//        return cleaningServiceService.getCleaningService(id);
//    }
//
//    @PutMapping("/end-service/{id}")
//    public void endCleaningService(@PathVariable Long id){
//        cleaningServiceService.endCleaningService(id);
//    }
//
//    @PutMapping("/finish-service/{id}")
//    public void finishCleaningService(@PathVariable Long id, @RequestParam String date){
//        cleaningServiceService.finishCleaningService(id, date);
//    }
//
//    @GetMapping("/dates-of-cleaning/{id}")
//    public List<CleaningDateDto> getDatesOfCleaningForCleaningService(@PathVariable Long id){
//        return cleaningServiceService.getDatesOfCleaningForCleaningService(id);
//    }
//
//    @PostMapping("/message/{id}")
//    public void addMessageToCleaningService(@PathVariable Long id, @RequestBody MessageDto dto){
//        cleaningServiceService.addMessageToCleaningService(id, dto);
//    }
//
//    @GetMapping("/messages/{id}")
//    public List<MessageDto> getMessagesForCleaningService(@PathVariable Long id){
//        return cleaningServiceService.getMessagesForCleaningService(id);
//    }
//
//    @GetMapping("/next-cleaning-date/{id}")
//    public CleaningDateDto getNextCleaningDate(@PathVariable Long id){
//        return cleaningServiceService.getNextCleaningDate(id);
//    }
//
//    @GetMapping("/descriptions")
//    public CleaningServiceDescriptionDto getDescriptions(){
//        return cleaningServiceService.getDescriptions();
//    }
//
//    @GetMapping("/prices")
//    public CleaningServicePricesDto getCleaningServicePrices(){
//        return cleaningServiceService.getCleaningServicePrices();
//    }
//
//    @GetMapping("/dates-to-reschedule/{id}")
//    public List<DatesToRescheduleDto> getDatesToReschedule(@PathVariable Long id){
//        return cleaningServiceService.getDatesToReschedule(id);
//    }
//
//    @PostMapping("/reschedule/{id}")
//    public void rescheduleCleaningService(@PathVariable Long id, @RequestBody RescheduleDateDto dto) {
//        cleaningServiceService.rescheduleCleaningService(id, dto);
//    }
}
