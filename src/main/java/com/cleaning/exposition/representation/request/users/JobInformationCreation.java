package com.cleaning.exposition.representation.request.users;

import com.cleaning.domain.users.*;
import com.cleaning.domain.users.job_information.*;
import lombok.*;

import java.time.*;

@AllArgsConstructor
@Getter
public class JobInformationCreation {
    private final String workPhone;
    private final LocalDate hiringDate;
    private final Long salary;

    public JobInformation toDomain() {
        return new JobInformation.JobInformationBuilder()
                .withWorkPhone(workPhone)
                .withHiringDate(hiringDate)
                .withSalary(salary)
                .build();
    }
}
