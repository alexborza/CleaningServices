package com.cleaning.exposition.representation.data;

import com.cleaning.exposition.representation.request.*;

import java.time.*;

public class EmployeeContractCreationTestData {

    public static EmployeeContractCreation dummyEmployeeContractCreation(String username, String email) {

        return new EmployeeContractCreation(
                username,
                email,
                "password",
                dummyUserInformationCreation("fullName", "address", "phoneNumber", "birthDate"),
                dummyJobInformationCreation("workPhone", LocalDate.now(), 2000L)
        );
    }

    public static UserInformationCreation dummyUserInformationCreation(String fullName, String address, String phoneNumber, String birthDate) {

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
