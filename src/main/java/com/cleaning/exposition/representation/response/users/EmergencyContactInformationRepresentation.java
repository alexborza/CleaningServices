package com.cleaning.exposition.representation.response.users;

import lombok.*;

@AllArgsConstructor
@Getter
public class EmergencyContactInformationRepresentation {
    private final String fullName;
    private final String address;
    private final String phoneNumber;
    private final String relationship;
}
