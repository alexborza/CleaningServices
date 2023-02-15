package com.cleaning.infrastructure.users;

import com.cleaning.domain.users.*;
import com.cleaning.infrastructure.implementation.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.annotation.*;
import org.springframework.test.context.junit.jupiter.*;
import org.springframework.transaction.annotation.*;

import javax.persistence.*;
import java.time.*;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JobInformationRepositoryTest {

    @Autowired
    private JobInformationRepositoryImplementation jobInformationRepositoryImplementation;

    @Test
    @DirtiesContext
    public void test_update_job_information() {


        JobInformation newJobInformation = new JobInformation.JobInformationBuilder()
                .withTitle("updatedTitle")
                .withSupervisor("updatedSupervisor")
                .withWorkPhone("updatedWorkPhone")
                .withHiringDate(LocalDate.of(2023, 2, 15))
                .withSalary(4000L)
                .withEmploymentStatus(EmploymentStatus.FULL_TIME)
                .build();

        jobInformationRepositoryImplementation.updateJobInformation(10001L, newJobInformation);

        Optional<JobInformation> optionalJobInformation = jobInformationRepositoryImplementation.findById(10001L);

        assertThat(optionalJobInformation).isNotEmpty();
        JobInformation jobInformation = optionalJobInformation.get();

        assertThat(jobInformation.getTitle()).isEqualTo(newJobInformation.getTitle());
        assertThat(jobInformation.getSupervisor()).isEqualTo(newJobInformation.getSupervisor());
        assertThat(jobInformation.getWorkPhone()).isEqualTo(newJobInformation.getWorkPhone());
        assertThat(jobInformation.getHiringDate()).isEqualTo(newJobInformation.getHiringDate());
        assertThat(jobInformation.getSalary()).isEqualTo(newJobInformation.getSalary());
        assertThat(jobInformation.getEmploymentStatus()).isEqualTo(newJobInformation.getEmploymentStatus());

    }
}
