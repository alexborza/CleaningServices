package com.cleaning.domain.users.data;

import com.cleaning.domain.users.job_information.*;

import java.time.*;

public class JobInformationTestData {

    public static JobInformation dummyJobInformationWithId(Long id) {
        return new JobInformation.JobInformationBuilder()
                .withId(id)
                .withWorkPhone("workPhone")
                .withHiringDate(LocalDate.now())
                .withSalary(2000L)
                .build();
    }

    public static JobInformation dummyJobInformation(String workPhone, LocalDate hiringDate, Long salary) {
        return new JobInformation.JobInformationBuilder()
                .withWorkPhone(workPhone)
                .withHiringDate(hiringDate)
                .withSalary(salary)
                .build();
    }

    public static JobInformation dummyJobInformation() {
        return dummyJobInformation("workPhone", LocalDate.now(), 2000L);
    }
}
