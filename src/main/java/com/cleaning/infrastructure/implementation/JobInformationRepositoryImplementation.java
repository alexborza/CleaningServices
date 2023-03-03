package com.cleaning.infrastructure.implementation;

import com.cleaning.domain.users.job_information.*;
import com.cleaning.infrastructure.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public class JobInformationRepositoryImplementation implements JobInformationRepository {

    @Autowired
    private JobInformationJpaRepository jpaRepository;

    @Override
    public void updateJobInformation(Long id, JobInformation representation) {

        jpaRepository.updateJobInformation(
                id,
                representation.getWorkPhone(),
                representation.getHiringDate(),
                representation.getSalary()
        );
    }

    @Override
    public Optional<JobInformation> findById(Long id) {

        return jpaRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {

        return jpaRepository.existsById(id);
    }
}
