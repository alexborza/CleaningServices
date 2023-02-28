package com.cleaning.exposition.representation.data;

import com.cleaning.exposition.representation.response.cleaning_service.*;

public class ContactInfoRepresentationTestData {

    public static ContactInfoRepresentation dummyContactInfoRepresentation() {

        return new ContactInfoRepresentation(
                "firstName",
                "lastName",
                "email",
                "phoneNumber"
        );
    }
}
