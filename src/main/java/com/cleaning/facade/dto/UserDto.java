package com.cleaning.facade.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.util.*;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EmployeeDto.class, name = "employee"),
})
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private UserInformationDto userInformation;
}