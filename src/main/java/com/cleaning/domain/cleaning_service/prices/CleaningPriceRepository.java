package com.cleaning.domain.cleaning_service.prices;

import java.util.*;

public interface CleaningPriceRepository {

    CleaningPrice save(CleaningPrice cleaningPrice);

    Optional<CleaningPrice> findTopByOrderByIdDesc();
}
