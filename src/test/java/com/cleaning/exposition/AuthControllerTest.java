package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.exposition.representation.data.*;
import com.cleaning.exposition.representation.request.users.*;
import com.cleaning.exposition.representation.response.users.*;
import com.cleaning.infrastructure.shared.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

    @Test
    public void testRegisterUser() {
        SignupRequest signupRequest = SignupRequestTestData.dummySignupRequest();

        ResponseEntity<Void> voidResponseEntity = authController.registerUser(signupRequest);

        assertNotNull(voidResponseEntity);
        assertEquals(voidResponseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testAuthenticateUser() {
        JwtResponse jwtResponse = JwtResponseTestData.dummyJwtResponse();
        LoginRequest loginRequest = LoginRequestTestData.dummyLoginRequest("username", "password");

        when(authService.authenticateUser(loginRequest)).thenReturn(jwtResponse);

        ResponseEntity<JwtResponse> response = authController.authenticateUser(loginRequest);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        JwtResponse body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getEmail()).isEqualTo(jwtResponse.getEmail());
        assertThat(body.getUsername()).isEqualTo(jwtResponse.getUsername());
        assertThat(body.getId()).isEqualTo(jwtResponse.getId());
        assertThat(body.getToken()).isEqualTo(jwtResponse.getToken());
        assertThat(body.getRole()).isEqualTo(jwtResponse.getRole());
    }
}
