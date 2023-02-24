package com.cleaning.infrastructure;

import com.cleaning.domain.cleaning_service.prices.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface CleaningPriceJpaRepository extends JpaRepository<CleaningPrice, Long> {

    Optional<CleaningPrice> findTopByOrderByIdDesc();
}
