package com.cleaning.repository;

import com.cleaning.entity.cleaning_service.description.*;
import org.springframework.data.repository.*;

import java.util.*;

public interface CleaningServiceDescriptionRepository extends CrudRepository<CleaningDescription, Long> {
    Optional<CleaningDescription> findFirstBy();
}
