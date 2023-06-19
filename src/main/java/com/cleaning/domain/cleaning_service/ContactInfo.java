package com.cleaning.domain.cleaning_service;

import com.cleaning.domain.*;
import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.*;

@Embeddable
@NoArgsConstructor
@Getter
public class ContactInfo extends BaseEntity {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    @NotBlank
    private String phoneNumber;

    public ContactInfo(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        validate(this);
    }
}
