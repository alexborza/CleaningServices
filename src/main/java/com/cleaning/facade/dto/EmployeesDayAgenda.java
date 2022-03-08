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
}
