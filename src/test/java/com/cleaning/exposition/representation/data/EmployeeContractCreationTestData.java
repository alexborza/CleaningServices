package com.cleaning.exposition.representation.data;

import com.cleaning.exposition.representation.request.users.*;

import java.time.*;

public class EmployeeContractCreationTestData {

    public static EmployeeContractCreation dummyEmployeeContractCreation(String username, String email) {

        return new EmployeeContractCreation(
                username,
                email,
                "password",
                dummyUserInformationCreation("fullName", "address", "phoneNumber", LocalDate.of(1999, 10, 19)),
                dummyJobInformationCreation("workPhone", LocalDate.now(), 2000L)
        );
    }

    public static UserInformationCreation dummyUserInformationCreation(String fullName, String address, String phoneNumber, LocalDate birthDate) {

        return new UserInformationCreation(
                fullName,
                address,
                phoneNumber,
                birthDate
        );
    }

    public static JobInformationCreation dummyJobInformationCreation(String workPhone, LocalDate hiringDate, Long salary) {

        return new JobInformationCreation(
                workPhone,
                hiringDate,
                salary
        );
    }
}
