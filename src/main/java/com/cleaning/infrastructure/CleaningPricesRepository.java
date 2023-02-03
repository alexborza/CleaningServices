package com.cleaning.infrastructure;

import com.cleaning.domain.cleaning_service.prices.*;
import org.springframework.data.jpa.repository.*;

public interface CleaningPricesRepository extends JpaRepository<CleaningPrices, Long> {

}
