package com.cleaning.facade.dto;

import com.cleaning.entity.appointment.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class EmployeesDayAgenda {
    private Long employeeId;
    private List<TimeSlot> timeSlots;
    private List<TimeSlot> availableIntervalsForOverlapping;

    public EmployeesDayAgenda(Long employeeId, List<TimeSlot> timeSlots){
        this.employeeId = employeeId;
        this.timeSlots = timeSlots;
    }
}
