package com.cleaning.exposition;

import com.cleaning.application.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/client")
public class ClientController {
//
//    @Autowired
//    private ClientService clientService;
//
//    @GetMapping("/cleaning-services/{userId}")
//    public List<CleaningServiceDto> getClientsCleaningServices(@PathVariable Long userId){
//        return clientService.getClientsCleaningServices(userId);
//    }
//
}