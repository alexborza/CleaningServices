package com.cleaning.exposition.representation.request;

import com.cleaning.domain.users.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class UserInformationCreation {
    private final String fullName;
    private final String address;
    private final String phoneNumber;
    private final String birthDate;

    public UserInformation toDomain() {
        return new UserInformation(
                fullName,
                address,
                phoneNumber,
                birthDate
        );
    }
}
