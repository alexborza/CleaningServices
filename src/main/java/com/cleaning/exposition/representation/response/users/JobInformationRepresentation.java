package com.cleaning.exposition.representation.response.users;

import com.cleaning.domain.users.job_information.*;
import lombok.*;

import java.time.*;

@AllArgsConstructor
@Getter
public class JobInformationRepresentation {
    private final Long id;
    private final String workPhone;
    private final LocalDate hiringDate;
    private final Long salary;

    public static JobInformationRepresentation fromDomain(JobInformation jobInformation) {
        return new JobInformationRepresentation(
                jobInformation.getId(),
                jobInformation.getWorkPhone(),
                jobInformation.getHiringDate(),
                jobInformation.getSalary()
        );
    }
}
