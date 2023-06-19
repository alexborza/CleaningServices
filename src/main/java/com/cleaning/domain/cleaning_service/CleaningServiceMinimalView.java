package com.cleaning.domain.cleaning_service;

import java.time.*;

public interface CleaningServiceMinimalView {
    Long getId();
    CleaningType getCleaningType();
    Double getTotal();
    Integer getTimeEstimation();
    LocalDate getNextCleaningDate();
    Integer getStartingHour();
    Integer getEndingHour();
}
