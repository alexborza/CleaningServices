package com.cleaning.domain.cleaning_service;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ContactInfo {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
