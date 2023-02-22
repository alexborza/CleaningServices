package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.exposition.representation.data.*;
import com.cleaning.exposition.representation.request.*;
import com.cleaning.exposition.representation.response.*;
import com.cleaning.infrastructure.shared.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

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
