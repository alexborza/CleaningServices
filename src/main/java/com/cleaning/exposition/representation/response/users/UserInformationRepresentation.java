package com.cleaning.exposition.representation.response.users;

import com.cleaning.domain.users.*;
import lombok.*;

import java.time.*;

@AllArgsConstructor
@Getter
public class UserInformationRepresentation {
    private final String fullName;
    private final String address;
    private final String phoneNumber;
    private final LocalDate birthDate;

    public static UserInformationRepresentation fromDomain(UserInformation userInformation) {
        return new UserInformationRepresentation(
                userInformation.getFullName(),
                userInformation.getAddress(),
                userInformation.getPhoneNumber(),
                userInformation.getBirthDate()
        );
    }
}
