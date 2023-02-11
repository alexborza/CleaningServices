package com.cleaning.exposition.representation.data;

import com.cleaning.exposition.representation.response.users.*;

public class UserInformationRepresentationTestData {

    public static UserInformationRepresentation dummyUserInformationRepresentation() {
        return new UserInformationRepresentation(
                "fullName",
                "address",
                "phoneNumber",
                "birthDate"
        );
    }
}
