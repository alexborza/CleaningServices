package com.cleaning.repository;

import com.cleaning.entity.*;
import org.springframework.data.repository.*;

import java.util.*;

public interface CleaningServiceDescriptionRepository extends CrudRepository<CleaningServiceDescription, Long> {
    Optional<CleaningServiceDescription> findFirstBy();
}
