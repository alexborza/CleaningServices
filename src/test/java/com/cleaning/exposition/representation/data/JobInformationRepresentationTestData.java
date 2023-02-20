package com.cleaning.exposition.representation.data;

import com.cleaning.domain.users.*;
import com.cleaning.exposition.representation.response.users.*;

import java.time.*;

public class JobInformationRepresentationTestData {

    public static JobInformationRepresentation dummyJobInformationRepresentation(Long id) {
        return new JobInformationRepresentation(
                id,
                "title",
                "supervisor",
                "workPhone",
                EmploymentStatus.FULL_TIME,
                LocalDate.now(),
                2000L
        );
    }
}
