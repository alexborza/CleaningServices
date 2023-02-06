package com.cleaning.infrastructure.users.data;

import com.cleaning.domain.users.*;

public class UserTestData {

    public static Client dummyClient() {
        return new Client(
                "clientUser",
                "clientEmail",
                "password",
                dummyUserInformation()
        );
    }

    public static Employee dummyEmployee() {
        return new Employee(
                "employeeUser",
                "employeeEmail",
                "password",
                dummyUserInformation(),
                dummyEmployeeInformation()
        );
    }

    private static UserInformation dummyUserInformation() {
        return new UserInformation("fullname", "address", "phoneNumber", "birthDate");
    }

    private static EmployeeInformation dummyEmployeeInformation() {
        return new EmployeeInformation(dummyJobInformation());
    }

    private static JobInformation dummyJobInformation() {
        return new JobInformation("title", "supervisor", "workPhone", "hiringDate", 2000L, EmploymentStatus.FULL_TIME);
    }
}
