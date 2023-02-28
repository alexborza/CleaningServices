package com.cleaning.application;

import com.cleaning.domain.users.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Transactional
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void registerUser(String username, String email, String password){
        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException("Username is already taken!");
        }
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("Email is already in use!");
        }

        String encodedPassword = encoder.encode(password);

        User user = new Client.Builder()
                .withUsername(username)
                .withEmail(email)
                .withPassword(encoder.encode(encodedPassword))
                .build();

        userRepository.save(user);
    }

    public User getUser(Long userId){
        //TODO: probably and where role <> 'ADMIN'
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public void updateEmail(Long userId, String email) {
        if(!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        userRepository.updateEmail(userId, email);
    }

    public void updatePassword(Long userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if(!encoder.matches(oldPassword, user.getPassword())){
            throw new PasswordNotMatchingException("Introduced password is not matching the existing password");
        }

        String encodedPassword = encoder.encode(newPassword);
        userRepository.updatePassword(userId, encodedPassword);
    }

    public void updateUserInformation(Long id, UserInformation userInformation){
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        userRepository.updateUserInformation(id, userInformation);
    }
}
