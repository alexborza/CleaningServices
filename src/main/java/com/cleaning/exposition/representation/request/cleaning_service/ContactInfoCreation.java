package com.cleaning.exposition.representation.request.cleaning_service;

import com.cleaning.domain.cleaning_service.*;
import lombok.*;

@Getter
@AllArgsConstructor
public class ContactInfoCreation {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;

    public ContactInfo toDomain() {
        return new ContactInfo(
                firstName,
                lastName,
                email,
                phoneNumber
        );
    }
}
