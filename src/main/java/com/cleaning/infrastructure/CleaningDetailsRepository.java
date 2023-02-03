package com.cleaning.infrastructure;

import com.cleaning.domain.cleaning_service.details.*;
import org.springframework.data.jpa.repository.*;

public interface CleaningDetailsRepository extends JpaRepository<CleaningDetails, Long> {
}
