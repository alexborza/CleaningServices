package com.cleaning.facade.dto;

import com.cleaning.entity.users.*;
import lombok.*;

@Data
public class JobInformationDto {
    private String title;
    private String supervisor;
    private String workPhone;
    private EmploymentStatus employmentStatus;
    private String hiringDate;
    private Long salary;
}
