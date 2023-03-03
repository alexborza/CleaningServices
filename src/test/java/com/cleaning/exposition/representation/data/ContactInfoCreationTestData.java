package com.cleaning.exposition.representation.data;

import com.cleaning.exposition.representation.request.cleaning_service.*;

public class ContactInfoCreationTestData {

    public static ContactInfoCreation dummyContactInfoCreation() {

        return new ContactInfoCreation(
                "firstName",
                "lastName",
                "email",
                "phoneNumber"
        );
    }
}
