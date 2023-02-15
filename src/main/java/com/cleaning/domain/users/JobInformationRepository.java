package com.cleaning.domain.users;

import java.util.*;

public interface JobInformationRepository {

    void updateJobInformation(Long id, JobInformation jobInformation);

    Optional<JobInformation> findById(Long id);

    boolean existsById(Long id);
}
