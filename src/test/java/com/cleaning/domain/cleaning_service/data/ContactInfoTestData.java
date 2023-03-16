package com.cleaning.domain.cleaning_service.data;

import com.cleaning.domain.cleaning_service.*;

public class ContactInfoTestData {

    public static ContactInfo dummyContactInfo() {
        return dummyContactInfo("firstName", "lastName", "email", "phoneNumber");
    }

    public static ContactInfo dummyContactInfo(String firstName, String lastName, String email, String phoneNumber) {
        return new ContactInfo(firstName, lastName, email, phoneNumber);
    }
}
