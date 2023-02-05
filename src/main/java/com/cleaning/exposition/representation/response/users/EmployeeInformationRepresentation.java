package com.cleaning.exposition.representation.response.users;

import lombok.*;

@AllArgsConstructor
@Getter
public class EmployeeInformationRepresentation {
    private final Long id;
    private final JobInformationRepresentation jobInformation;
    private final EmergencyContactInformationRepresentation emergencyContactInformation;
}
