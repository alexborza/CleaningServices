package com.cleaning.infrastructure;

import com.cleaning.domain.cleaning_service.prices.*;
import org.springframework.data.repository.*;

public interface CleaningServicePricesRepository extends CrudRepository<CleaningServicePrices, Long> {

}
