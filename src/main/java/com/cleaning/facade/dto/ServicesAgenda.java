package com.cleaning.facade.dto;

import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServicesAgenda {
    private String employeeName;
    private List<CleaningServiceDto> cleaningServices;
}
