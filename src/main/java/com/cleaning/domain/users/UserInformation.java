package com.cleaning.domain.users;

import lombok.*;

import javax.persistence.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserInformation {
    private String fullName;
    private String address;
    private String phoneNumber;
    private String birthDate;
}

