package com.cleaning.facade;

import com.cleaning.entity.*;
import com.cleaning.facade.dto.*;
import com.cleaning.facade.dto.request.*;
import com.cleaning.facade.dto.response.*;
import com.cleaning.facade.vo.*;
import com.cleaning.repository.*;
import com.cleaning.security.jwt.*;
import com.cleaning.security.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class AuthFacade {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    public JwtResponse authenticateUser(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse(null);

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                role);
    }

    public ResponseEntity<MessageResponse> registerUser(SignupRequest signUpRequest){
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Email is already in use!"));
        }
        // Create new user's account
        User user = this.createNewUserAccount(signUpRequest);
        user.setRole(ERole.ROLE_USER);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    private User createNewUserAccount(SignupRequest signUpRequest){
        return new Client(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
    }

    public List<UserCredentialDto> getExistingUserCredentials(){
        List<UserCredentialVo> userCredentialVos = userRepository.getUserCredentials();
        return userCredentialVos.stream()
                .map(vo -> new UserCredentialDto(vo.getUsername(), vo.getEmail()))
                .collect(Collectors.toList());
    }
}