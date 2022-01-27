package com.cleaning.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class ContactInfo {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
