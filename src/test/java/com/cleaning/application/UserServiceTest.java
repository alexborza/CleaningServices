package com.cleaning.application;

import com.cleaning.domain.users.*;
import com.cleaning.domain.users.data.*;
import com.cleaning.domain.users.exception.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.security.crypto.password.*;

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

    private static final String PASSWORD = "password";
    private static final String NEW_PASSWORD = "newPassword";

    @Test
    public void testShouldGetUser() {
        User user = UserTestData.dummyClient("username", "email");

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));

        User user1 = userService.getUser(1L);

        assert user != null;
        assertEquals(user1.getUsername(), user.getUsername());
        assertEquals(user1.getPassword(), user.getPassword());
        assertEquals(user1.getEmail(), user.getEmail());
        assertThat(user1).isInstanceOf(Client.class);
    }

    @Test
    public void testShouldThrowUserNotFoundWhenGetUser() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> userService.getUser(id));

        assertEquals(exception.getMessage(), "User not found for id = " + id);
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
    public void testShouldThrowUserNotFoundExceptionWhenUpdateEmail() {
        Long id = 1L;
        String email = "newEmail";

        when(userRepository.existsById(id)).thenReturn(false);

        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> userService.updateEmail(id, email));

        assertEquals(exception.getMessage(), "User not found for id = " + id);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void shouldUpdatePassword() {
        Long id = 1L;
        User user = UserTestData.dummyClient("username", "email");
        String encodedPassword = "encodedPassword";

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        assert user != null;
        when(encoder.matches(PASSWORD, user.getPassword())).thenReturn(true);
        when(encoder.encode(NEW_PASSWORD)).thenReturn(encodedPassword);

        userService.updatePassword(id, PASSWORD, NEW_PASSWORD);

        verify(userRepository).updatePassword(id, encodedPassword);
    }

    @Test
    public void shouldThrowUserNotFoundExceptionWhenUpdatePassword() {
        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> userService.updatePassword(id, PASSWORD, NEW_PASSWORD));

        assertEquals(exception.getMessage(), "User not found for id = " + id);

        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(encoder);
    }

    @Test
    public void shouldThrowPasswordNotMatchingExceptionWhenUpdatePassword() {
        Long id = 1L;
        User user = UserTestData.dummyClient("username", "email");

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        assert user != null;
        when(encoder.matches(PASSWORD, user.getPassword())).thenReturn(false);

        PasswordNotMatchingException exception = assertThrows(PasswordNotMatchingException.class,
                () -> userService.updatePassword(id, PASSWORD, NEW_PASSWORD));

        assertEquals(exception.getMessage(), "Introduced password is not matching the existing password");

        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(encoder);
    }

    @Test
    public void shouldUpdateUserInformation() {
        Long id = 1L;
        UserInformation userInformation = UserTestData.dummyUserInformation();

        when(userRepository.existsById(id)).thenReturn(true);

        userService.updateUserInformation(id, userInformation);

        verify(userRepository).updateUserInformation(any(), any());
    }

    @Test
    public void shouldThrowUserNotFoundExceptionWhenUpdateUserInformation() {
        Long id = 1L;
        UserInformation userInformation = UserTestData.dummyUserInformation();

        when(userRepository.existsById(id)).thenReturn(false);

        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> userService.updateUserInformation(id, userInformation));

        assertEquals(exception.getMessage(), "User not found for id = " + id);

        verifyNoMoreInteractions(userRepository);
    }
}
