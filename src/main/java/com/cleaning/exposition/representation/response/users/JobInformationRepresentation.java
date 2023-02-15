package com.cleaning.exposition.representation.response.users;

import com.cleaning.domain.users.*;
import lombok.*;

import java.time.*;

@AllArgsConstructor
@Getter
public class JobInformationRepresentation {
    private final Long id;
    private final String title;
    private final String supervisor;
    private final String workPhone;
    private final EmploymentStatus employmentStatus;
    private final LocalDate hiringDate;
    private final Long salary;

    public static JobInformationRepresentation fromDomain(JobInformation jobInformation) {
        return new JobInformationRepresentation(
                jobInformation.getId(),
                jobInformation.getTitle(),
                jobInformation.getSupervisor(),
                jobInformation.getWorkPhone(),
                jobInformation.getEmploymentStatus(),
                jobInformation.getHiringDate(),
                jobInformation.getSalary()
        );
    }

    public JobInformation toDomain() {
        return new JobInformation.JobInformationBuilder()
                .withTitle(title)
                .withSupervisor(supervisor)
                .withWorkPhone(workPhone)
                .withHiringDate(hiringDate)
                .withSalary(salary)
                .withEmploymentStatus(employmentStatus)
                .build();
    }
}
