package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.exposition.representation.request.*;
import com.cleaning.exposition.representation.response.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class AuthController {
//
//    @Autowired
//    private AuthService authService;
//
//    @PostMapping("/signin")
//    public JwtResponse authenticateUser(@RequestBody LoginRequest loginRequest) {
//        return authService.authenticateUser(loginRequest);
//
//    }
//    @PostMapping("/signup")
//    public ResponseEntity<MessageResponse> registerUser(@RequestBody SignupRequest signUpRequest) {
//        return authService.registerUser(signUpRequest);
//    }
//
//    @GetMapping("/existing-credentials")
//    public List<UserCredentialDto> getExistingUserCredentials(){
//        return authService.getExistingUserCredentials();
//    }
}
