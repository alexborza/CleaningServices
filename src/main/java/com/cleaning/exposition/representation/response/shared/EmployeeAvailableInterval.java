package com.cleaning.exposition.representation.response.shared;

import com.cleaning.exposition.representation.response.appointment.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class EmployeeAvailableInterval implements Comparable<EmployeeAvailableInterval> {
    private final Long employeeId;
    private final TimeSlotRepresentation availableInterval;
    private final boolean includedLunchBreak;

    @Override
    public int compareTo(EmployeeAvailableInterval o) {
        return this.availableInterval.compareTo(o.getAvailableInterval());
    }
}
