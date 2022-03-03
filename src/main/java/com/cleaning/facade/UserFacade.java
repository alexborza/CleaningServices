package com.cleaning.facade;

import com.cleaning.entity.*;
import com.cleaning.facade.dto.*;
import com.cleaning.facade.dto.response.*;
import com.cleaning.facade.mapper.*;
import com.cleaning.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import javax.persistence.*;

@Service
public class UserFacade {
    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private AdministratorMapper administratorMapper;

    @Autowired
    private PasswordEncoder encoder;

    public UserDto getUser(Long userId){
        User client = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
        return administratorMapper.toUserDto(client);
    }

    public void modifyEmail(Long userId, String email) {
        User client = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
        client.setEmail(email);
        userRepository.save(client);
    }

    public ResponseEntity<MessageResponse> modifyPassword(Long userId, ModifyPasswordDto dto) {
        User client = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
        if(!encoder.matches(dto.getPassword(), client.getPassword())){
            return ResponseEntity.badRequest().body(new MessageResponse("Incorrect password entered!"));
        }
        client.setPassword(encoder.encode(dto.getNewPassword()));
        userRepository.save(client);
        return ResponseEntity.ok(new MessageResponse("Successfully modified the password!"));
    }

    public void modifyPersonalInfo(Long id, UserInformationDto dto){
        User client = userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        client.setUserInformation(administratorMapper.userInformationDtoToUserInformation(dto));
        userRepository.save(client);
    }
}
