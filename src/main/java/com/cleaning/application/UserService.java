package com.cleaning.application;

import com.cleaning.domain.users.*;
import com.cleaning.exposition.representation.request.*;
import com.cleaning.exposition.representation.response.users.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import javax.persistence.*;

@Transactional
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void registerUser(SignupRequest signUpRequest){
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new UserAlreadyExistsException("Username is already taken!");
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new UserAlreadyExistsException("Email is already in use!");
        }

        User user = new Client.Builder()
                .withUsername(signUpRequest.getUsername())
                .withEmail(signUpRequest.getEmail())
                .withPassword(encoder.encode(signUpRequest.getPassword()))
                .build();

        userRepository.save(user);
    }

    public UserRepresentation getUser(Long userId){
        //probably and where role <> 'ADMIN'
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found for id: " + userId.toString()));

        return UserRepresentation.fromDomain(user);
    }

    public void updateEmail(Long userId, String email) {
        if(!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found for id: " + userId.toString());
        }

        userRepository.updateEmail(userId, email);
    }

    public void updatePassword(Long userId, ModifyPassword representation) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found for id: " + userId.toString()));

        if(!encoder.matches(representation.getPassword(), user.getPassword())){
            throw new PasswordNotMatchingException("Introduced password is not matching the existing password");
        }

        String encodedPassword = encoder.encode(representation.getNewPassword());
        userRepository.updatePassword(userId, encodedPassword);
    }

    public void updateUserInformation(Long id, UserInformationRepresentation representation){
        if(!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found for id: " + id.toString());
        }

        userRepository.updateUserInformation(id, representation.toDomain());
    }
}
