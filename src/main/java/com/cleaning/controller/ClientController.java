package com.cleaning.controller;

import com.cleaning.facade.*;
import com.cleaning.facade.dto.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientFacade facade;

    @GetMapping("/cleaning-services/{userId}")
    public List<CleaningServiceDto> getClientsCleaningServices(@PathVariable Long userId){
        return facade.getClientsCleaningServices(userId);
    }

}
