package com.cleaning.exposition.representation.response.shared;

import com.cleaning.exposition.representation.response.appointment.*;
import lombok.*;

@Getter
public class EmployeeAvailableInterval implements Comparable<EmployeeAvailableInterval> {
    private final Long employeeId;
    private final TimeSlotRepresentation availableInterval;
    private final boolean includedLunchBreak;
    private final String interval;

    public EmployeeAvailableInterval(Long employeeId, TimeSlotRepresentation availableInterval, boolean includedLunchBreak, String interval) {
        this.employeeId = employeeId;
        this.availableInterval = availableInterval;
        this.includedLunchBreak = includedLunchBreak;
        this.interval = interval;
    }

    @Override
    public int compareTo(EmployeeAvailableInterval o) {
        return this.availableInterval.compareTo(o.getAvailableInterval());
    }
}
