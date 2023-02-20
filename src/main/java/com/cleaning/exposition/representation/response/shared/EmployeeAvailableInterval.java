package com.cleaning.exposition.representation.response.shared;

import com.cleaning.exposition.representation.response.appointment.*;
import lombok.*;

import java.util.*;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeAvailableInterval that = (EmployeeAvailableInterval) o;
        return Objects.equals(availableInterval, that.availableInterval);
    }

    @Override
    public int hashCode() {
        return Objects.hash(availableInterval);
    }
}
