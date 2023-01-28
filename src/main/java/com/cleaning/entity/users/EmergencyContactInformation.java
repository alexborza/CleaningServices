package com.cleaning.entity.users;

import lombok.*;

import javax.persistence.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmergencyContactInformation {
    private String fullName;
    private String address;
    private String phoneNumber;
    private String relationship;
}
