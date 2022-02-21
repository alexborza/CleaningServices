package com.cleaning.facade.dto;

import lombok.*;

@Getter
@AllArgsConstructor
public class UserInformationDto {
    private final String fullName;
    private final String address;
    private final String phoneNumber;
    private final String birthDate;
}
