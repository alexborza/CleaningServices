package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.exposition.representation.response.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{userId}")
    public UserDto getUser(@PathVariable Long userId){
        return userService.getUser(userId);
    }

    @PostMapping("/email/{userId}")
    public void modifyEmail(@PathVariable Long userId, @RequestBody String email) {
        userService.modifyEmail(userId, email);
    }

    @PostMapping("/password/{userId}")
    public ResponseEntity<MessageResponse> modifyPassword(@PathVariable Long userId, @RequestBody ModifyPasswordDto dto) {
        return userService.modifyPassword(userId, dto);
    }

    @PostMapping("/personal-info/{userId}")
    public void modifyPersonalInfo(@PathVariable Long userId, @RequestBody UserInformationDto dto) {
        userService.modifyPersonalInfo(userId, dto);
    }
}
