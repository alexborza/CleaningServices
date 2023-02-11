package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.exposition.representation.request.*;
import com.cleaning.exposition.representation.response.users.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> registerUser(@RequestBody SignupRequest signUpRequest) {
        userService.registerUser(signUpRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserRepresentation> getUser(@PathVariable Long userId){
        UserRepresentation user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/email/{userId}")
    public ResponseEntity<Void> updateEmail(@PathVariable Long userId, @RequestBody String email) {
        userService.updateEmail(userId, email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/password/{userId}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long userId, @RequestBody ModifyPassword representation) {
        userService.updatePassword(userId, representation);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user-info/{userId}")
    public ResponseEntity<Void> updateUserInformation(@PathVariable Long userId, @RequestBody UserInformationRepresentation representation) {
        userService.updateUserInformation(userId, representation);
        return ResponseEntity.ok().build();
    }
}
