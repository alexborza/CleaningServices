package com.cleaning.infrastructure.users.data;

import com.cleaning.domain.users.*;

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
                .withEmployeeInformation(dummyEmployeeInformation())
                .build();
    }

    public static UserInformation dummyUserInformation(String fullName, String address, String phoneNumber, String birthDate) {
        return new UserInformation(fullName, address, phoneNumber, birthDate);
    }

    private static EmployeeInformation dummyEmployeeInformation() {
        return new EmployeeInformation(dummyJobInformation());
    }

    private static JobInformation dummyJobInformation() {
        return new JobInformation("title", "supervisor", "workPhone", "hiringDate", 2000L, EmploymentStatus.FULL_TIME);
    }
}
