package com.cleaning.facade.dto;

import com.cleaning.entity.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class EmployeesDayAgenda {
    private Long employeeId;
    private List<AvailableInterval> availableIntervals;
    private List<AvailableInterval> availableIntervalsForOverlapping;

    public EmployeesDayAgenda(Long employeeId, List<AvailableInterval> availableIntervals){
        this.employeeId = employeeId;
        this.availableIntervals = availableIntervals;
    }
}
