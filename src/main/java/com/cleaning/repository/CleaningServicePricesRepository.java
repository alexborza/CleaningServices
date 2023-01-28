package com.cleaning.repository;

import com.cleaning.entity.cleaning_service.prices.*;
import org.springframework.data.repository.*;

import java.util.*;

public interface CleaningServicePricesRepository extends CrudRepository<CleaningServicePrices, Long> {
    Optional<CleaningServicePrices> findFirstBy();
}
