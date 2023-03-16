package com.cleaning.domain.users.job_information;

import com.cleaning.domain.*;
import com.cleaning.domain.users.data.*;
import org.junit.jupiter.api.*;

import java.time.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class JobInformationTest {

    @Test
    void should_create() {
        JobInformation jobInformation = JobInformationTestData.dummyJobInformation(
                "workPhone",
                LocalDate.of(2023, 3, 16),
                2000L);

        assertThat(jobInformation).isNotNull();
        assertThat(jobInformation.getWorkPhone()).isEqualTo("workPhone");
        assertThat(jobInformation.getHiringDate()).isEqualTo(LocalDate.of(2023, 3, 16));
        assertThat(jobInformation.getSalary()).isEqualTo(2000L);
    }

    @Test
    void should_not_create_job_information_with_null_work_phone() {

        assertThrows(DomainConstraintViolationException.class,
                () -> JobInformationTestData.dummyJobInformation(
                        null,
                        LocalDate.now(),
                        2000L
                ));
    }

    @Test
    void should_not_create_job_information_with_empty_work_phone() {

        assertThrows(DomainConstraintViolationException.class,
                () -> JobInformationTestData.dummyJobInformation(
                        "",
                        LocalDate.now(),
                        2000L
                ));
    }

    @Test
    void should_not_create_job_information_with_null_hiring_date() {

        assertThrows(DomainConstraintViolationException.class,
                () -> JobInformationTestData.dummyJobInformation(
                        "workPhone",
                        null,
                        2000L
                ));
    }

    @Test
    void should_not_create_job_information_with_null_salary() {

        assertThrows(DomainConstraintViolationException.class,
                () -> JobInformationTestData.dummyJobInformation(
                        "workPhone",
                        LocalDate.now(),
                        null
                ));
    }
}
