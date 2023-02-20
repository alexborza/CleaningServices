package com.cleaning.infrastructure.users.data;

import com.cleaning.domain.users.*;

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
                .withJobInformation(dummyJobInformation("title", "supervisor", "workPhone", LocalDate.of(2023, 2, 1), 2000L, EmploymentStatus.FULL_TIME))
                .build();
    }

    public static Employee dummyEmployeeWithId(Long id, String username, String email) {
        return new Employee.Builder()
                .withId(id)
                .withUsername(username)
                .withEmail(email)
                .withPassword("password")
                .withUserInformation(dummyUserInformation("fullName", "address", "phoneNumber", "birthDate"))
                .withJobInformation(dummyJobInformation("title", "supervisor", "workPhone", LocalDate.of(2023, 2, 1), 2000L, EmploymentStatus.FULL_TIME))
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
                .withTitle("title")
                .withSupervisor("supervisor")
                .withWorkPhone("workphone")
                .withHiringDate(LocalDate.now())
                .withSalary(2000L)
                .withEmploymentStatus(EmploymentStatus.FULL_TIME)
                .build();
    }

    public static JobInformation dummyJobInformation(String title, String supervisor, String workphone, LocalDate hiringDate, Long salary, EmploymentStatus employmentStatus) {
        return new JobInformation.JobInformationBuilder()
                .withTitle(title)
                .withSupervisor(supervisor)
                .withWorkPhone(workphone)
                .withHiringDate(hiringDate)
                .withSalary(salary)
                .withEmploymentStatus(employmentStatus)
                .build();
    }
}
