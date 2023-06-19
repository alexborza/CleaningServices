package com.cleaning.domain.cleaning_service.description;

import java.util.*;

public interface CleaningDescriptionRepository {

    CleaningDescription save(CleaningDescription cleaningDescription);

    Optional<CleaningDescription> findTopByOrderByIdDesc();
}
