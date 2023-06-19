package com.cleaning.infrastructure;

import com.cleaning.domain.users.job_information.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

import javax.transaction.*;
import java.time.*;

public interface JobInformationJpaRepository extends JpaRepository<JobInformation, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE JobInformation jobInformation SET " +
            "jobInformation.workPhone = :workPhone, " +
            "jobInformation.hiringDate = :hiringDate, " +
            "jobInformation.salary = :salary " +
            "WHERE jobInformation.id = :id")
    void updateJobInformation(
            @Param("id") Long id,
            @Param("workPhone") String workPhone,
            @Param("hiringDate") LocalDate hiringDate,
            @Param("salary") Long salary);
}
