package com.cleaning.exposition.representation.response.users;

import com.cleaning.domain.users.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class EmployeeInformationRepresentation {
    private final Long id;
    private final JobInformationRepresentation jobInformation;

    public static EmployeeInformationRepresentation fromDomain(EmployeeInformation employeeInformation) {
        return new EmployeeInformationRepresentation(
                employeeInformation.getId(),
                JobInformationRepresentation.fromDomain(employeeInformation.getJobInformation())
        );
    }

    public EmployeeInformation toDomain() {
        return new EmployeeInformation(
                getJobInformation().toDomain()
        );
    }
}
