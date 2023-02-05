package com.cleaning.exposition.representation.response.cleaning_service;

import lombok.*;

@Getter
@AllArgsConstructor
public class ContactInfoRepresentation {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
}
