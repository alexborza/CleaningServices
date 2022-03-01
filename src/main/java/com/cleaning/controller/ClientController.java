package com.cleaning.controller;

import com.cleaning.facade.*;
import com.cleaning.facade.dto.*;
import com.cleaning.facade.dto.response.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientFacade facade;

    @GetMapping("{userId}")
    public UserDto getUser(@PathVariable Long userId){
        return facade.getUser(userId);
    }

    @PostMapping("/email/{userId}")
    public void modifyEmail(@PathVariable Long userId, @RequestBody String email) {
        facade.modifyEmail(userId, email);
    }

    @PostMapping("/password/{userId}")
    public ResponseEntity<MessageResponse> modifyPassword(@PathVariable Long userId, @RequestBody ModifyPasswordDto dto) {
        return facade.modifyPassword(userId, dto);
    }

    @PostMapping("/personal-info/{userId}")
    public void modifyPersonalInfo(@PathVariable Long userId, @RequestBody UserInformationDto dto) {
        facade.modifyPersonalInfo(userId, dto);
    }

    @GetMapping("/cleaning-services/{userId}")
    public List<CleaningServiceDto> getClientsCleaningServices(@PathVariable Long userId){
        return facade.getClientsCleaningServices(userId);
    }

    @GetMapping("/office-cleanings/{userId}")
    public List<OfficeCleaningDto> getClientsOfficeCleanings(@PathVariable Long userId){
        return facade.getClientsOfficeCleanings(userId);
    }

}
