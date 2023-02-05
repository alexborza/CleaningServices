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
}
