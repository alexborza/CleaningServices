package com.cleaning.application;

import com.cleaning.domain.users.*;
import com.cleaning.exposition.representation.request.*;
import com.cleaning.exposition.representation.response.*;
import com.cleaning.infrastructure.*;
import com.cleaning.security.jwt.*;
import com.cleaning.security.implementation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class AuthService {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserRepository<User> userRepository;
//
//    @Autowired
//    private PasswordEncoder encoder;
//
//    @Autowired
//    private JwtUtils jwtUtils;
//
//    public JwtResponse authenticateUser(LoginRequest loginRequest){
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken(authentication);
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        String role = userDetails.getAuthorities().stream()
//                .findFirst()
//                .map(GrantedAuthority::getAuthority)
//                .orElse(null);
//
//        return new JwtResponse(jwt,
//                userDetails.getId(),
//                userDetails.getUsername(),
//                userDetails.getEmail(),
//                role);
//    }
//
//    public ResponseEntity<MessageResponse> registerUser(SignupRequest signUpRequest){
//        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Username is already taken!"));
//        }
//        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Email is already in use!"));
//        }
//        // Create new user's account
//        User user = this.createNewUserAccount(signUpRequest);
//        userRepository.save(user);
//        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//    }
//
//    private User createNewUserAccount(SignupRequest signUpRequest){
//        return new Client(
//                signUpRequest.getUsername(),
//                signUpRequest.getEmail(),
//                encoder.encode(signUpRequest.getPassword()),
//                Collections.emptyList());
//    }
//
//    public List<UserCredentialDto> getExistingUserCredentials(){
//        return null;
//    }
}