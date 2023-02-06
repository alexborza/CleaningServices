package com.cleaning.exposition.representation.data;

import com.cleaning.domain.users.*;
import com.cleaning.exposition.representation.response.users.*;

public class UserRepresentationTestData {

    public static UserRepresentation dummyClientRepresentation(Long id) {
        return new ClientRepresentation(
                id,
                "clientUser",
                "clientEmail",
                "clientPassword",
                dummyUserInformationRepresentation()
        );
    }

    public static UserRepresentation dummyEmployeeRepresentation(Long id, EmployeeInformationRepresentation employeeInformationRepresentation) {
        return new EmployeeRepresentation(
                id,
                "employeeUser",
                "employeeEmail",
                "employeePassword",
                dummyUserInformationRepresentation(),
                employeeInformationRepresentation
        );
    }

    private static UserInformationRepresentation dummyUserInformationRepresentation() {
        return new UserInformationRepresentation(
                "fullName",
                "address",
                "phoneNumber",
                "birthDate"
        );
    }

    public static EmployeeInformationRepresentation dummyEmployeeInformationRepresentation() {
        return new EmployeeInformationRepresentation(
                1L,
                dummyJobInformationRepresentation()
        );
    }

    private static JobInformationRepresentation dummyJobInformationRepresentation() {
        return new JobInformationRepresentation(
                "title",
                "supervisor",
                "workPhone",
                EmploymentStatus.FULL_TIME,
                "hiringDate",
                2000L
        );
    }
}
