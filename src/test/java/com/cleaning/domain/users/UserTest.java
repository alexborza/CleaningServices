package com.cleaning.domain.users;

import com.cleaning.domain.*;
import com.cleaning.domain.users.data.*;
import org.junit.jupiter.api.*;

import java.time.*;

import static com.cleaning.domain.users.data.JobInformationTestData.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void should_create_client() {
        Client client = UserTestData.dummyClient("username", "email");

        assertThat(client).isNotNull();
        assertThat(client.getUsername()).isEqualTo("username");
        assertThat(client.getEmail()).isEqualTo("email");
        assertThat(client.getPassword()).isEqualTo("password");
        assertThat(client.getRole()).isEqualTo(Role.CLIENT);
        assertThat(client.getUserInformation().getFullName()).isEqualTo("fullName");
        assertThat(client.getUserInformation().getPhoneNumber()).isEqualTo("phoneNumber");
        assertThat(client.getUserInformation().getAddress()).isEqualTo("address");
        assertThat(client.getUserInformation().getBirthDate()).isEqualTo(LocalDate.now());
    }

    @Test
    void should_create_employee() {
        Employee employee = UserTestData.dummyEmployee("username", "email", dummyJobInformation());

        assertThat(employee).isNotNull();
        assertThat(employee.getUsername()).isEqualTo("username");
        assertThat(employee.getEmail()).isEqualTo("email");
        assertThat(employee.getPassword()).isEqualTo("password");
        assertThat(employee.getRole()).isEqualTo(Role.EMPLOYEE);
        assertThat(employee.getUserInformation().getFullName()).isEqualTo("fullName");
        assertThat(employee.getUserInformation().getPhoneNumber()).isEqualTo("phoneNumber");
        assertThat(employee.getUserInformation().getAddress()).isEqualTo("address");
        assertThat(employee.getUserInformation().getBirthDate()).isEqualTo(LocalDate.now());

        assertThat(employee.getJobInformation().getWorkPhone()).isEqualTo("workPhone");
        assertThat(employee.getJobInformation().getHiringDate()).isEqualTo(LocalDate.now());
        assertThat(employee.getJobInformation().getSalary()).isEqualTo(2000L);
    }

    @Test
    void should_create_admin() {
        Admin admin = UserTestData.dummyAdmin("username", "email", "password");

        assertThat(admin).isNotNull();
        assertThat(admin.getUsername()).isEqualTo("username");
        assertThat(admin.getEmail()).isEqualTo("email");
        assertThat(admin.getPassword()).isEqualTo("password");
        assertThat(admin.getRole()).isEqualTo(Role.ADMIN);
    }

    @Test
    void should_not_create_client_with_null_username() {

        assertThrows(DomainConstraintViolationException.class,
                () -> UserTestData.dummyClient(
                        null,
                        "email",
                        "password"
                ));
    }

    @Test
    void should_not_create_client_with_blank_username() {

        assertThrows(DomainConstraintViolationException.class,
                () -> UserTestData.dummyClient(
                        "",
                        "email",
                        "password"
                ));
    }

    @Test
    void should_not_create_client_with_null_email() {

        assertThrows(DomainConstraintViolationException.class,
                () -> UserTestData.dummyClient(
                        "username",
                        null,
                        "password"
                ));
    }

    @Test
    void should_not_create_client_with_blank_email() {

        assertThrows(DomainConstraintViolationException.class,
                () -> UserTestData.dummyClient(
                        "username",
                        "",
                        "password"
                ));
    }

    @Test
    void should_not_create_client_with_null_password() {

        assertThrows(DomainConstraintViolationException.class,
                () -> UserTestData.dummyClient(
                        "username",
                        "email",
                        null
                ));
    }

    @Test
    void should_not_create_client_with_blank_password() {

        assertThrows(DomainConstraintViolationException.class,
                () -> UserTestData.dummyClient(
                        "username",
                        "email",
                        ""
                ));
    }

    @Test
    void should_not_create_employee_with_null_job_information() {

        assertThrows(DomainConstraintViolationException.class,
                () -> UserTestData.dummyEmployee(
                        "username",
                        "email",
                        null
                ));
    }
}
