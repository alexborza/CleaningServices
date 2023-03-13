package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.domain.users.*;
import com.cleaning.exposition.representation.data.*;
import com.cleaning.exposition.representation.request.users.*;
import com.cleaning.exposition.representation.response.users.*;
import com.cleaning.infrastructure.users.data.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.*;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder encoder;

    @Test
    public void testRegisterUser() {
        SignupRequest signupRequest = SignupRequestTestData.dummySignupRequest();

        ResponseEntity<Void> voidResponseEntity = userController.registerUser(signupRequest);

        assertNotNull(voidResponseEntity);
        assertEquals(voidResponseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetUser() {
        Long id = 1L;
        User user = UserTestData.dummyClient("username", "email");

        when(userService.getUser(id)).thenReturn(user);

        ResponseEntity<UserRepresentation> response = userController.getUser(id);

        UserRepresentation body = response.getBody();

        assertNotNull(response);
        assertNotNull(body);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(body.getUsername(), user.getUsername());
        assertEquals(body.getPassword(), user.getPassword());
        assertEquals(body.getEmail(), user.getEmail());
    }

    @Test
    public void testUpdateEmail() {

        ResponseEntity<Void> response = userController.updateEmail(1L, "updatedEmail");

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testUpdatePassword() {

        ResponseEntity<Void> response = userController.updatePassword(1L, ModifyPasswordTestData.dummyModifyPassword());

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testUpdateUserInformation() {
        UserInformationCreation userInformationCreation =
                EmployeeContractCreationTestData.dummyUserInformationCreation("fullName", "address", "phoneNumber", LocalDate.of(1999, 10, 19));
        ResponseEntity<Void> response = userController.updateUserInformation(1L, userInformationCreation);

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
