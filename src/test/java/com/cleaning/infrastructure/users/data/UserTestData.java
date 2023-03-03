package com.cleaning.infrastructure.users.data;

import com.cleaning.domain.users.*;
import com.cleaning.domain.users.job_information.*;

import java.time.*;

public class UserTestData {

    public static Client dummyClient(String username, String email) {
        return new Client.Builder()
                .withUsername(username)
                .withEmail(email)
                .withPassword("password")
                .withUserInformation(dummyUserInformation("fullName", "address", "phoneNumber", "birthDate"))
                .build();
    }

    public static Employee dummyEmployee(String username, String email) {
        return new Employee.Builder()
                .withUsername(username)
                .withEmail(email)
                .withPassword("password")
                .withUserInformation(dummyUserInformation("fullName", "address", "phoneNumber", "birthDate"))
                .withJobInformation(dummyJobInformation("workPhone", LocalDate.of(2023, 2, 1), 2000L))
                .build();
    }

    public static Employee dummyEmployeeWithId(Long id, String username, String email) {
        return new Employee.Builder()
                .withId(id)
                .withUsername(username)
                .withEmail(email)
                .withPassword("password")
                .withUserInformation(dummyUserInformation("fullName", "address", "phoneNumber", "birthDate"))
                .withJobInformation(dummyJobInformation("workPhone", LocalDate.of(2023, 2, 1), 2000L))
                .build();
    }

    public static UserInformation dummyUserInformation(String fullName, String address, String phoneNumber, String birthDate) {
        return new UserInformation(fullName, address, phoneNumber, birthDate);
    }

    public static UserInformation dummyUserInformation() {
        return new UserInformation("fullName", "address", "phoneNumber", "birthDate");
    }

    public static JobInformation dummyJobInformation() {
        return new JobInformation.JobInformationBuilder()
                .withWorkPhone("workphone")
                .withHiringDate(LocalDate.now())
                .withSalary(2000L)
                .build();
    }

    public static JobInformation dummyJobInformation(String workphone, LocalDate hiringDate, Long salary) {
        return new JobInformation.JobInformationBuilder()
                .withWorkPhone(workphone)
                .withHiringDate(hiringDate)
                .withSalary(salary)
                .build();
    }
}
