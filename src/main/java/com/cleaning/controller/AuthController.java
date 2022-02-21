package com.cleaning.controller;

import com.cleaning.facade.*;
import com.cleaning.facade.dto.*;
import com.cleaning.facade.dto.request.*;
import com.cleaning.facade.dto.response.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthFacade authFacade;

    @PostMapping("/signin")
    public JwtResponse authenticateUser(@RequestBody LoginRequest loginRequest) {
        return authFacade.authenticateUser(loginRequest);

    }
    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody SignupRequest signUpRequest) {
        return authFacade.registerUser(signUpRequest);
    }

    @GetMapping("/existing-credentials")
    public List<UserCredentialDto> getExistingUserCredentials(){
        return authFacade.getExistingUserCredentials();
    }
}
