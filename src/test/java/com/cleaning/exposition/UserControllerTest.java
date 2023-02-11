package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.exposition.representation.data.*;
import com.cleaning.exposition.representation.request.*;
import com.cleaning.exposition.representation.response.users.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

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
        UserRepresentation representation = UserRepresentationTestData.dummyClientRepresentation(1L);

        when(userService.getUser(id)).thenReturn(representation);

        ResponseEntity<UserRepresentation> response = userController.getUser(id);

        UserRepresentation body = response.getBody();

        assertNotNull(response);
        assertNotNull(body);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(body.getUsername(), representation.getUsername());
        assertEquals(body.getPassword(), representation.getPassword());
        assertEquals(body.getEmail(), representation.getEmail());
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
        UserInformationRepresentation representation = UserInformationRepresentationTestData.dummyUserInformationRepresentation();
        ResponseEntity<Void> response = userController.updateUserInformation(1L, representation);

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
