package com.cleaning.exposition.representation;

import com.cleaning.domain.users.*;
import com.cleaning.exposition.representation.data.*;
import com.cleaning.exposition.representation.response.users.*;
import com.cleaning.infrastructure.users.data.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRepresentationTest {

    @Test
    public void toDomainClient() {
        UserRepresentation representation = UserRepresentationTestData.dummyClientRepresentation(1L);
        UserInformationRepresentation informationRepresentation = representation.getUserInformation();

        User client = representation.toDomain();
        UserInformation userInformation = client.getUserInformation();

        assertThat(client).isInstanceOf(Client.class);
        assertEquals(client.getRole(), Role.USER);
        assertEquals(client.getEmail(), representation.getEmail());
        assertEquals(client.getPassword(), representation.getPassword());
        assertEquals(client.getUsername(), representation.getUsername());
        assertEquals(userInformation.getAddress(), informationRepresentation.getAddress());
        assertEquals(userInformation.getBirthDate(), informationRepresentation.getBirthDate());
        assertEquals(userInformation.getFullName(), informationRepresentation.getFullName());
        assertEquals(userInformation.getPhoneNumber(), informationRepresentation.getPhoneNumber());
    }

    @Test
    public void fromDomainClient() {
        Client client = UserTestData.dummyClient();
        UserRepresentation representation = UserRepresentation.fromDomain(client);
        UserInformationRepresentation userInformationRepresentation = representation.getUserInformation();
        UserInformation userInformation = client.getUserInformation();

        assertThat(representation).isInstanceOf(ClientRepresentation.class);
        assertEquals(representation.getEmail(), client.getEmail());
        assertEquals(representation.getUsername(), client.getUsername());
        assertEquals(representation.getPassword(), client.getPassword());
        assertEquals(userInformationRepresentation.getAddress(), userInformation.getAddress());
        assertEquals(userInformationRepresentation.getBirthDate(), userInformation.getBirthDate());
        assertEquals(userInformationRepresentation.getPhoneNumber(), userInformation.getPhoneNumber());
        assertEquals(userInformationRepresentation.getFullName(), userInformation.getFullName());
    }

    public void fromDomainEmployee() {
        Employee employee = UserTestData.dummyEmployee();
        UserRepresentation representation = UserRepresentation.fromDomain(employee);
        UserInformationRepresentation userInformationRepresentation = representation.getUserInformation();
        UserInformation userInformation = employee.getUserInformation();


        assertThat(representation).isInstanceOf(EmployeeRepresentation.class);
        EmployeeRepresentation employeeRepresentation = (EmployeeRepresentation) representation;
        JobInformationRepresentation jobInformationRepresentation = employeeRepresentation.getEmployeeInformation().getJobInformation();
        JobInformation jobInformation = employee.getEmployeeInformation().getJobInformation();

        assertEquals(representation.getEmail(), employee.getEmail());
        assertEquals(representation.getUsername(), employee.getUsername());
        assertEquals(representation.getPassword(), employee.getPassword());
        assertEquals(userInformationRepresentation.getAddress(), userInformation.getAddress());
        assertEquals(userInformationRepresentation.getBirthDate(), userInformation.getBirthDate());
        assertEquals(userInformationRepresentation.getPhoneNumber(), userInformation.getPhoneNumber());
        assertEquals(userInformationRepresentation.getFullName(), userInformation.getFullName());
        assertEquals(jobInformationRepresentation.getSalary(), jobInformation.getSalary());
        assertEquals(jobInformationRepresentation.getWorkPhone(), jobInformation.getWorkPhone());
        assertEquals(jobInformationRepresentation.getSupervisor(), jobInformation.getSupervisor());
        assertEquals(jobInformationRepresentation.getTitle(), jobInformation.getTitle());
        assertEquals(jobInformationRepresentation.getHiringDate(), jobInformation.getHiringDate());
        assertEquals(jobInformationRepresentation.getEmploymentStatus(), jobInformation.getEmploymentStatus());
    }

    @Test
    public void toDomainEmployee() {
        EmployeeInformationRepresentation employeeInformationRepresentation = UserRepresentationTestData.dummyEmployeeInformationRepresentation();
        UserRepresentation representation = UserRepresentationTestData.dummyEmployeeRepresentation(1L, employeeInformationRepresentation);
        UserInformationRepresentation informationRepresentation = representation.getUserInformation();

        User user = representation.toDomain();
        UserInformation userInformation = user.getUserInformation();

        assertThat(user).isInstanceOf(Employee.class);
        Employee employee = (Employee) user;
        JobInformation jobInformation = employee.getEmployeeInformation().getJobInformation();
        JobInformationRepresentation jobInformationRepresentation = employeeInformationRepresentation.getJobInformation();

        assertEquals(employee.getRole(), Role.EMPLOYEE);
        assertEquals(employee.getEmail(), representation.getEmail());
        assertEquals(employee.getPassword(), representation.getPassword());
        assertEquals(employee.getUsername(), representation.getUsername());
        assertEquals(userInformation.getAddress(), informationRepresentation.getAddress());
        assertEquals(userInformation.getBirthDate(), informationRepresentation.getBirthDate());
        assertEquals(userInformation.getFullName(), informationRepresentation.getFullName());
        assertEquals(userInformation.getPhoneNumber(), informationRepresentation.getPhoneNumber());
        assertEquals(jobInformation.getEmploymentStatus(), jobInformationRepresentation.getEmploymentStatus());
        assertEquals(jobInformation.getHiringDate(), jobInformationRepresentation.getHiringDate());
        assertEquals(jobInformation.getSalary(), jobInformationRepresentation.getSalary());
        assertEquals(jobInformation.getSupervisor(), jobInformationRepresentation.getSupervisor());
        assertEquals(jobInformation.getTitle(), jobInformationRepresentation.getTitle());
        assertEquals(jobInformation.getWorkPhone(), jobInformationRepresentation.getWorkPhone());
    }
}
