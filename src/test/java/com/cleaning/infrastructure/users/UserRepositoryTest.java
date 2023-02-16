package com.cleaning.infrastructure.users;

import com.cleaning.domain.users.*;
import com.cleaning.infrastructure.implementation.*;
import com.cleaning.infrastructure.users.data.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.annotation.*;
import org.springframework.test.context.junit.jupiter.*;

import java.util.*;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepositoryImplementation userRepositoryImplementation;

    @Test
    public void test_find_by_id() {
        Optional<User> optionalUser = userRepositoryImplementation.findById(10001L);

        assertThat(optionalUser).isNotEmpty();
        User user = optionalUser.get();

        assertThat(user.getEmail()).isEqualTo("clientEmail");
        assertThat(user.getPassword()).isEqualTo("clientPass");
        assertThat(user.getRole()).isEqualTo(Role.USER);
        assertThat(user.getUsername()).isEqualTo("clientUsername");
        assertThat(user.getUserInformation().getFullName()).isEqualTo("Clientul fericit");
        assertThat(user.getUserInformation().getPhoneNumber()).isEqualTo("0742032456");
        assertThat(user.getUserInformation().getAddress()).isEqualTo("strada 2");
        assertThat(user.getUserInformation().getBirthDate()).isEqualTo("1993-02-03");
    }

    @Test
    public void test_find_all_by_role() {
        addUsers();

        //keep in mind that I have one more dummy Client added in data.sql file
        List<User> clients = userRepositoryImplementation.findAllByRole(Role.USER);

        assertThat(clients).hasSize(5);
        assertThat(clients.stream().map(User::getUsername).collect(Collectors.toList())).containsExactlyInAnyOrder(
                "user1", "user2", "user3", "user4", "clientUsername"
        );
        assertThat(clients).hasOnlyElementsOfType(Client.class);

    }

    @Test
    public void test_find_all_employee_ids() {
        addUsers();
        List<Long> employeeIds = userRepositoryImplementation.findAllEmployeeIds();

        assertThat(employeeIds).hasSize(4);
    }

    @Test
    public void test_exists_by_id() {
        boolean exists = userRepositoryImplementation.existsById(10001L);
        assertThat(exists).isTrue();
    }

    @Test
    public void test_exists_by_username() {
        boolean exists = userRepositoryImplementation.existsByUsername("clientUsername");
        assertThat(exists).isTrue();
    }

    @Test
    public void test_exists_by_email() {
        boolean exists = userRepositoryImplementation.existsByEmail("clientEmail");
        assertThat(exists).isTrue();
    }

    @Test
    @DirtiesContext
    public void test_update_email() {
        userRepositoryImplementation.updateEmail(10001L, "updatedEmail");
        Optional<User> optionalUser = userRepositoryImplementation.findById(10001L);
        assertThat(optionalUser).isNotEmpty();
        assertThat(optionalUser.get().getEmail()).isEqualTo("updatedEmail");
    }

    @Test
    @DirtiesContext
    public void test_update_password() {
        userRepositoryImplementation.updatePassword(10001L, "updatedPassword");
        Optional<User> optionalUser = userRepositoryImplementation.findById(10001L);
        assertThat(optionalUser).isNotEmpty();
        assertThat(optionalUser.get().getPassword()).isEqualTo("updatedPassword");
    }

    @Test
    @DirtiesContext
    public void test_update_user_information() {
        UserInformation newUserInformation = UserTestData.dummyUserInformation(
                "updatedFullName",
                "updatedAddress",
                "updatedPhoneNumber",
                "updatedBirthDate");

        userRepositoryImplementation.updateUserInformation(10001L, newUserInformation);
        Optional<User> optionalUser = userRepositoryImplementation.findById(10001L);
        assertThat(optionalUser).isNotEmpty();
        User user = optionalUser.get();
        UserInformation userInformation = user.getUserInformation();
        assertThat(userInformation.getFullName()).isEqualTo(newUserInformation.getFullName());
        assertThat(userInformation.getAddress()).isEqualTo(newUserInformation.getAddress());
        assertThat(userInformation.getBirthDate()).isEqualTo(newUserInformation.getBirthDate());
        assertThat(userInformation.getPhoneNumber()).isEqualTo(newUserInformation.getPhoneNumber());
    }

    private void addUsers() {
        Client client1 = UserTestData.dummyClient("user1", "email1");
        Client client2 = UserTestData.dummyClient("user2", "email2");
        Client client3 = UserTestData.dummyClient("user3", "email3");
        Client client4 = UserTestData.dummyClient("user4", "email4");
        Employee employee1 = UserTestData.dummyEmployee("e1_username", "e1_email");
        Employee employee2 = UserTestData.dummyEmployee("e2_username", "e2_email");
        Employee employee3 = UserTestData.dummyEmployee("e3_username", "e3_email");

        userRepositoryImplementation.saveAll(List.of(client1, client2, client3, client4, employee1, employee2, employee3));
    }
}
