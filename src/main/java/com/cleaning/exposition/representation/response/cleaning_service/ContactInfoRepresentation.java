package com.cleaning.exposition.representation.response.cleaning_service;

import com.cleaning.domain.cleaning_service.*;
import lombok.*;

@Getter
@AllArgsConstructor
public class ContactInfoRepresentation {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;

    public static ContactInfoRepresentation fromDomain(ContactInfo contactInfo) {
        return new ContactInfoRepresentation(
                contactInfo.getFirstName(),
                contactInfo.getLastName(),
                contactInfo.getEmail(),
                contactInfo.getPhoneNumber()
        );
    }
}
