package com.cleaning.infrastructure;

import com.cleaning.domain.cleaning_service.description.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface CleaningDescriptionRepository extends JpaRepository<CleaningDescription, Long> {
    Optional<CleaningDescription> findFirstBy();
}
