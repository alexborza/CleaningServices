package com.cleaning.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContactInfoDto {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
}
