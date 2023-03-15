package com.cleaning.domain.cleaning_service;

import com.cleaning.domain.*;
import com.cleaning.infrastructure.cleaning_service.data.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContactInfoTest {

    @Test
    void should_create() {
        ContactInfo contactInfo = ContactInfoTestData.dummyContactInfo();

        assertThat(contactInfo.getFirstName()).isEqualTo("firstName");
        assertThat(contactInfo.getLastName()).isEqualTo("lastName");
        assertThat(contactInfo.getEmail()).isEqualTo("email");
        assertThat(contactInfo.getPhoneNumber()).isEqualTo("phoneNumber");
    }

    @Test
    void should_not_create_when_first_name_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> ContactInfoTestData.dummyContactInfo(null, "l", "e", "p"));
    }

    @Test
    void should_not_create_when_first_name_blank() {
        assertThrows(DomainConstraintViolationException.class,
                () -> ContactInfoTestData.dummyContactInfo("  ", "l", "e", "p"));
    }

    @Test
    void should_not_create_when_last_name_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> ContactInfoTestData.dummyContactInfo("f", null, "e", "p"));
    }

    @Test
    void should_not_create_when_last_name_blank() {
        assertThrows(DomainConstraintViolationException.class,
                () -> ContactInfoTestData.dummyContactInfo("f", "", "e", "p"));
    }

    @Test
    void should_not_create_when_email_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> ContactInfoTestData.dummyContactInfo("f", "l", null, "p"));
    }

    @Test
    void should_not_create_when_email_blank() {
        assertThrows(DomainConstraintViolationException.class,
                () -> ContactInfoTestData.dummyContactInfo("f", "l", " ", "p"));
    }

    @Test
    void should_not_create_when_phone_number_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> ContactInfoTestData.dummyContactInfo("f", "l", "e", null));
    }

    @Test
    void should_not_create_when_phone_number_blank() {
        assertThrows(DomainConstraintViolationException.class,
                () -> ContactInfoTestData.dummyContactInfo("f", "l", "e", ""));
    }
}
