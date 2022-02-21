package com.cleaning.entity;

import lombok.*;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
public class UserInformation {
    private String fullName;
    private String address;
    private String phoneNumber;
    private String birthDate;
}

