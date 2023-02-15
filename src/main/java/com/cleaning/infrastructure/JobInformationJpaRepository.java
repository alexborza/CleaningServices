package com.cleaning.infrastructure;

import com.cleaning.domain.users.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

import javax.transaction.*;
import java.time.*;

public interface JobInformationJpaRepository extends JpaRepository<JobInformation, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE JobInformation jobInformation SET " +
            "jobInformation.title = :title, " +
            "jobInformation.supervisor = :supervisor, " +
            "jobInformation.workPhone = :workPhone, " +
            "jobInformation.employmentStatus = :employmentStatus, " +
            "jobInformation.hiringDate = :hiringDate, " +
            "jobInformation.salary = :salary " +
            "WHERE jobInformation.id = :id")
    void updateJobInformation(
            @Param("id") Long id,
            @Param("title") String title,
            @Param("supervisor") String supervisor,
            @Param("workPhone") String workPhone,
            @Param("employmentStatus") EmploymentStatus employmentStatus,
            @Param("hiringDate") LocalDate hiringDate,
            @Param("salary") Long salary);
}
