package com.cleaning.exposition.representation.request.users;

import com.cleaning.domain.users.*;
import lombok.*;

import java.time.*;

@AllArgsConstructor
@Getter
public class UserInformationCreation {
    private final String fullName;
    private final String address;
    private final String phoneNumber;
    private final LocalDate birthDate;

    public UserInformation toDomain() {
        return new UserInformation(
                fullName,
                address,
                phoneNumber,
                birthDate
        );
    }
}
