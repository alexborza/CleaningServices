package com.cleaning.infrastructure;

import com.cleaning.domain.cleaning_service.prices.*;
import org.springframework.data.jpa.repository.*;

public interface CleaningPriceJpaRepository extends JpaRepository<CleaningPrice, Long> {

}
