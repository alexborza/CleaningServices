package com.cleaning.facade.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@JsonTypeName("employee")
public class EmployeeDto extends UserDto {
    private EmployeeInformationDto employeeInformation;
}
