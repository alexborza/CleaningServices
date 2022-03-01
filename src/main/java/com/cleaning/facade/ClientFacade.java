package com.cleaning.facade;

import com.cleaning.entity.*;
import com.cleaning.facade.dto.*;
import com.cleaning.facade.dto.response.*;
import com.cleaning.facade.mapper.*;
import com.cleaning.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.*;

@Service
public class ClientFacade {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CleaningServiceRepository cleaningServiceRepository;

    @Autowired
    private OfficeCleaningRepository officeCleaningRepository;

    @Autowired
    private AdministratorMapper administratorMapper;

    @Autowired
    private CleaningServiceMapper cleaningServiceMapper;

    @Autowired
    private OfficeCleaningServiceMapper officeCleaningServiceMapper;

    @Autowired
    private PasswordEncoder encoder;

    public UserDto getUser(Long userId){
        User client = userRepository.findClientById(userId)
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
            return ResponseEntity.badRequest().body(new MessageResponse("Incorrect password entered"));
        }
        client.setPassword(encoder.encode(dto.getNewPassword()));
        userRepository.save(client);
        return ResponseEntity.ok(new MessageResponse("Successfully modified the password"));
    }

    public void modifyPersonalInfo(Long id, UserInformationDto dto){
        User client = userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        client.setUserInformation(administratorMapper.userInformationDtoToUserInformation(dto));
        userRepository.save(client);
    }

    public List<CleaningServiceDto> getClientsCleaningServices(Long userId){
        List<CleaningService> cleaningServices = cleaningServiceRepository.getClientsCleaningServices(userId);
        return cleaningServices.stream()
                .map(cleaningServiceMapper::toCleaningServiceDto)
                .collect(Collectors.toList());
    }

    public List<OfficeCleaningDto> getClientsOfficeCleanings(Long userId){
        List<OfficeCleaning> cleaningServices = officeCleaningRepository.getClientsOfficeCleanings(userId);
        return cleaningServices.stream()
                .map(officeCleaningServiceMapper::toOfficeCleaningDto)
                .collect(Collectors.toList());
    }
}
