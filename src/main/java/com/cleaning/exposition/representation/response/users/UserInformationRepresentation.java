package com.cleaning.exposition.representation.response.users;

import com.cleaning.domain.users.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class UserInformationRepresentation {
    private final String fullName;
    private final String address;
    private final String phoneNumber;
    private final String birthDate;

    public static UserInformationRepresentation fromDomain(UserInformation userInformation) {
        return new UserInformationRepresentation(
                userInformation.getFullName(),
                userInformation.getAddress(),
                userInformation.getPhoneNumber(),
                userInformation.getBirthDate()
        );
    }

    public UserInformation toDomain() {
        return new UserInformation(
                getFullName(),
                getAddress(),
                getPhoneNumber(),
                getBirthDate()
        );
    }
}
