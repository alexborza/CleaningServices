package com.cleaning.exposition.representation.response.users;

import com.cleaning.domain.users.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class JobInformationRepresentation {
    private final String title;
    private final String supervisor;
    private final String workPhone;
    private final EmploymentStatus employmentStatus;
    private final String hiringDate;
    private final Long salary;

    public static JobInformationRepresentation fromDomain(JobInformation jobInformation) {
        return new JobInformationRepresentation(
                jobInformation.getTitle(),
                jobInformation.getSupervisor(),
                jobInformation.getWorkPhone(),
                jobInformation.getEmploymentStatus(),
                jobInformation.getHiringDate(),
                jobInformation.getSalary()
        );
    }

    public JobInformation toDomain() {
        return new JobInformation(
                getTitle(),
                getSupervisor(),
                getWorkPhone(),
                getHiringDate(),
                getSalary(),
                getEmploymentStatus()
        );
    }
}
