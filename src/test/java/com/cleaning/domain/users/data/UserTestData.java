package com.cleaning.domain.users.data;

import com.cleaning.domain.users.*;
import com.cleaning.domain.users.job_information.*;

import java.time.*;

public class UserTestData {

    public static Admin dummyAdmin(String username, String email, String password) {
        return new Admin.Builder()
                .withUsername(username)
                .withEmail(email)
                .withPassword(password)
                .withUserInformation(null)
                .build();
    }

    public static Client dummyClient(String username, String email, String password) {
        return new Client.Builder()
                .withUsername(username)
                .withEmail(email)
                .withPassword(password)
                .withUserInformation(dummyUserInformation("fullName", "address", "phoneNumber", LocalDate.now()))
                .build();
    }

    public static Client dummyClient(String username, String email) {
        return dummyClient(username, email, "password");
    }

    public static Employee dummyEmployee(String username, String email, JobInformation jobInformation) {
        return new Employee.Builder()
                .withUsername(username)
                .withEmail(email)
                .withPassword("password")
                .withUserInformation(dummyUserInformation("fullName", "address", "phoneNumber", LocalDate.now()))
                .withJobInformation(jobInformation)
                .build();
    }

    public static Employee dummyEmployee(String username, String email) {

        return dummyEmployee(username, email, JobInformationTestData.dummyJobInformation("workPhone", LocalDate.of(2023, 2, 1), 2000L));
    }

    public static Employee dummyEmployeeWithId(Long id, String username, String email) {
        return new Employee.Builder()
                .withId(id)
                .withUsername(username)
                .withEmail(email)
                .withPassword("password")
                .withUserInformation(dummyUserInformation("fullName", "address", "phoneNumber", LocalDate.now()))
                .withJobInformation(JobInformationTestData.dummyJobInformation("workPhone", LocalDate.of(2023, 2, 1), 2000L))
                .build();
    }

    public static UserInformation dummyUserInformation(String fullName, String address, String phoneNumber, LocalDate birthDate) {
        return new UserInformation(fullName, address, phoneNumber, birthDate);
    }

    public static UserInformation dummyUserInformation() {
        return new UserInformation("fullName", "address", "phoneNumber", LocalDate.now());
    }
}
