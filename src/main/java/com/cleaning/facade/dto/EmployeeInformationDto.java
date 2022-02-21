package com.cleaning.facade.dto;

import lombok.*;

@Data
public class EmployeeInformationDto {
    private Long id;
    private JobInformationDto jobInformation;
    private EmergencyContactInformationDto emergencyContactInformation;
}
