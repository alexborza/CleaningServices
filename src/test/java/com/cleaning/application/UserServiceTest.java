package com.cleaning.application;

import com.cleaning.domain.users.*;
import com.cleaning.exposition.representation.data.*;
import com.cleaning.exposition.representation.request.*;
import com.cleaning.exposition.representation.response.users.*;
import com.cleaning.infrastructure.users.data.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.security.crypto.password.*;

import javax.persistence.*;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder encoder;

    @Test
    public void testShouldRegisterUser() {
        SignupRequest signupRequest = SignupRequestTestData.dummySignupRequest();

        when(userRepository.existsByUsername(signupRequest.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(signupRequest.getEmail())).thenReturn(false);

        userService.registerUser(signupRequest);

        verify(userRepository).save(any());
    }

    @Test
    public void testShouldNotRegisterUserExistingUsername() {
        SignupRequest signupRequest = SignupRequestTestData.dummySignupRequest();

        when(userRepository.existsByUsername(signupRequest.getUsername())).thenReturn(true);

        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class,
                () -> userService.registerUser(signupRequest));

        assertEquals(exception.getMessage(), "Username is already taken!");
    }

    @Test
    public void testShouldNotRegisterUserExistingEmail() {
        SignupRequest signupRequest = SignupRequestTestData.dummySignupRequest();

        when(userRepository.existsByUsername(signupRequest.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(signupRequest.getEmail())).thenReturn(true);

        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class,
                () -> userService.registerUser(signupRequest));

        assertEquals(exception.getMessage(), "Email is already in use!");
    }

    @Test
    public void testShouldGetUser() {
        User user = UserTestData.dummyClient("username", "email");

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));

        UserRepresentation representation = userService.getUser(1L);

        assert user != null;
        assertEquals(representation.getUsername(), user.getUsername());
        assertEquals(representation.getPassword(), user.getPassword());
        assertEquals(representation.getEmail(), user.getEmail());
        assertThat(representation).isInstanceOf(ClientRepresentation.class);
    }

    @Test
    public void testShouldThrowEntityNotFoundWhenGetUser() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> userService.getUser(id));

        assertEquals(exception.getMessage(), "User not found for id: " + id);
    }

    @Test
    public void testShouldUpdateEmail() {
        Long id = 1L;
        String email = "newEmail";

        when(userRepository.existsById(id)).thenReturn(true);

        userService.updateEmail(id, email);

        verify(userRepository).updateEmail(id, email);
    }

    @Test
    public void testShouldThrowEntityNotFoundExceptionWhenUpdateEmail() {
        Long id = 1L;
        String email = "newEmail";

        when(userRepository.existsById(id)).thenReturn(false);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> userService.updateEmail(id, email));

        assertEquals(exception.getMessage(), "User not found for id: " + id);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void shouldUpdatePassword() {
        Long id = 1L;
        User user = UserTestData.dummyClient("username", "email");
        ModifyPassword modifyPassword = ModifyPasswordTestData.dummyModifyPassword();
        String encodedPassword = "encodedPassword";

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        assert user != null;
        when(encoder.matches(modifyPassword.getPassword(), user.getPassword())).thenReturn(true);
        when(encoder.encode(modifyPassword.getNewPassword())).thenReturn(encodedPassword);

        userService.updatePassword(id, modifyPassword);

        verify(userRepository).updatePassword(id, encodedPassword);
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenUpdatePassword() {
        Long id = 1L;
        ModifyPassword modifyPassword = ModifyPasswordTestData.dummyModifyPassword();

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> userService.updatePassword(id, modifyPassword));

        assertEquals(exception.getMessage(), "User not found for id: " + id);

        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(encoder);
    }

    @Test
    public void shouldThrowPasswordNotMatchingExceptionWhenUpdatePassword() {
        Long id = 1L;
        User user = UserTestData.dummyClient("username", "email");
        ModifyPassword modifyPassword = ModifyPasswordTestData.dummyModifyPassword();

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        assert user != null;
        when(encoder.matches(modifyPassword.getPassword(), user.getPassword())).thenReturn(false);

        PasswordNotMatchingException exception = assertThrows(PasswordNotMatchingException.class,
                () -> userService.updatePassword(id, modifyPassword));

        assertEquals(exception.getMessage(), "Introduced password is not matching the existing password");

        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(encoder);
    }

    @Test
    public void shouldUpdateUserInformation() {
        Long id = 1L;
        UserInformationRepresentation representation = UserInformationRepresentationTestData.dummyUserInformationRepresentation();

        when(userRepository.existsById(id)).thenReturn(true);

        userService.updateUserInformation(id, representation);

        verify(userRepository).updateUserInformation(any(), any());
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenUpdateUserInformation() {
        Long id = 1L;
        UserInformationRepresentation representation = UserInformationRepresentationTestData.dummyUserInformationRepresentation();

        when(userRepository.existsById(id)).thenReturn(false);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> userService.updateUserInformation(id, representation));

        assertEquals(exception.getMessage(), "User not found for id: " + id);

        verifyNoMoreInteractions(userRepository);
    }
}
