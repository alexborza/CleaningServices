package com.cleaning.exposition.representation.response.shared;

import com.cleaning.exposition.representation.response.appointment.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class EmployeeAvailableIntervals implements Comparable<EmployeeAvailableIntervals> {
    private final Long employeeId;
    private final TimeSlotRepresentation availableInterval;

    @Override
    public int compareTo(EmployeeAvailableIntervals o) {
        return this.availableInterval.compareTo(o.getAvailableInterval());
    }
}
