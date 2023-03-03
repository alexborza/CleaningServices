package com.cleaning.exposition.representation;

import com.cleaning.domain.users.*;
import com.cleaning.domain.users.job_information.*;
import com.cleaning.exposition.representation.data.*;
import com.cleaning.exposition.representation.response.users.*;
import com.cleaning.infrastructure.users.data.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserRepresentationTest {

    @Test
    public void fromDomainClient() {
        Client client = UserTestData.dummyClient("dummyUser", "dummyEmail");
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

    @Test
    public void fromDomainEmployee() {
        Employee employee = UserTestData.dummyEmployee("dummyEmployeeUser", "dummyEmployeeEmail");
        UserRepresentation representation = UserRepresentation.fromDomain(employee);
        UserInformationRepresentation userInformationRepresentation = representation.getUserInformation();
        UserInformation userInformation = employee.getUserInformation();


        assertThat(representation).isInstanceOf(EmployeeRepresentation.class);
        EmployeeRepresentation employeeRepresentation = (EmployeeRepresentation) representation;
        JobInformationRepresentation jobInformationRepresentation = employeeRepresentation.getJobInformationRepresentation();
        JobInformation jobInformation = employee.getJobInformation();

        assertEquals(representation.getEmail(), employee.getEmail());
        assertEquals(representation.getUsername(), employee.getUsername());
        assertEquals(representation.getPassword(), employee.getPassword());
        assertEquals(userInformationRepresentation.getAddress(), userInformation.getAddress());
        assertEquals(userInformationRepresentation.getBirthDate(), userInformation.getBirthDate());
        assertEquals(userInformationRepresentation.getPhoneNumber(), userInformation.getPhoneNumber());
        assertEquals(userInformationRepresentation.getFullName(), userInformation.getFullName());
        assertEquals(jobInformationRepresentation.getSalary(), jobInformation.getSalary());
        assertEquals(jobInformationRepresentation.getWorkPhone(), jobInformation.getWorkPhone());
        assertEquals(jobInformationRepresentation.getHiringDate(), jobInformation.getHiringDate());
    }
}
