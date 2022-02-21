package com.cleaning.entity;

import lombok.*;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
public class EmergencyContactInformation {
    private String fullName;
    private String address;
    private String phoneNumber;
    private String relationship;
}
