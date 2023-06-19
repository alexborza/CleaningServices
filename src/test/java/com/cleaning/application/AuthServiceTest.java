package com.cleaning.application;

import com.cleaning.domain.users.*;
import com.cleaning.domain.users.exception.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.security.crypto.password.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder encoder;

    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";


    @Test
    public void testShouldRegisterUser() {

        when(userRepository.existsByUsername(USERNAME)).thenReturn(false);
        when(userRepository.existsByEmail(EMAIL)).thenReturn(false);
        when(encoder.encode(PASSWORD)).thenReturn("encodedPass");

        authService.registerUser(USERNAME, EMAIL, PASSWORD);

        verify(userRepository).save(any());
    }

    @Test
    public void testShouldNotRegisterUserExistingUsername() {

        when(userRepository.existsByUsername(USERNAME)).thenReturn(true);

        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class,
                () -> authService.registerUser(USERNAME, EMAIL, PASSWORD));

        assertEquals(exception.getMessage(), "Username is already taken!");
    }

    @Test
    public void testShouldNotRegisterUserExistingEmail() {

        when(userRepository.existsByUsername(USERNAME)).thenReturn(false);
        when(userRepository.existsByEmail(EMAIL)).thenReturn(true);

        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class,
                () -> authService.registerUser(USERNAME, EMAIL, PASSWORD));

        assertEquals(exception.getMessage(), "Email is already in use!");
    }
}
