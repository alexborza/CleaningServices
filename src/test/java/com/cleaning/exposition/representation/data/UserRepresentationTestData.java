package com.cleaning.exposition.representation.data;

import com.cleaning.domain.users.*;
import com.cleaning.exposition.representation.response.users.*;

import java.time.*;

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

    public static UserRepresentation dummyEmployeeRepresentation(Long id, JobInformationRepresentation jobInformationRepresentation) {
        return new EmployeeRepresentation(
                id,
                "employeeUser",
                "employeeEmail",
                "employeePassword",
                dummyUserInformationRepresentation(),
                jobInformationRepresentation
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

    public static JobInformationRepresentation dummyJobInformationRepresentation() {
        return new JobInformationRepresentation(
                1L,
                "title",
                "supervisor",
                "workPhone",
                EmploymentStatus.FULL_TIME,
                LocalDate.now(),
                2000L
        );
    }
}
