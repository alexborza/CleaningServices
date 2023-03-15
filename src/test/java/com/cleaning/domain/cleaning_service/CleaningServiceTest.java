package com.cleaning.domain.cleaning_service;

import com.cleaning.domain.*;
import com.cleaning.infrastructure.cleaning_service.data.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CleaningServiceTest {

    @Test
    void should_create() {
        CleaningService cleaningService = CleaningServiceTestData.dummyCleaningService(null);

        assertThat(cleaningService.getContactInfo()).isNotNull();
        assertThat(cleaningService.getLocation()).isNotNull();
        assertThat(cleaningService.getCleaningDetails()).isNotNull();
        assertThat(cleaningService.getClient()).isNull();
        assertThat(cleaningService.getFrequency()).isEqualTo(Frequency.ONE_TIME);
        assertThat(cleaningService.getPayment()).isEqualTo(Payment.CARD);
        assertThat(cleaningService.getMessages()).hasSize(2);
        assertThat(cleaningService.getCleaningType()).isEqualTo(CleaningType.STANDARD);
        assertThat(cleaningService.getTotal()).isEqualTo(100.0);
        assertThat(cleaningService.getTimeEstimation()).isEqualTo(3);
    }

    @Test
    void should_not_create_when_contact_info_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningServiceTestData.dummyCleaningServiceWithContactInfo(null));
    }

    @Test
    void should_not_create_when_location_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningServiceTestData.dummyCleaningServiceWithLocation(null));
    }

    @Test
    void should_not_create_when_cleaning_details_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningServiceTestData.dummyCleaningServiceWithCleaningDetails(null));
    }

    @Test
    void should_not_create_when_frequency_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningServiceTestData.dummyCleaningServiceWithFrequency(null));
    }

    @Test
    void should_not_create_when_payment_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningServiceTestData.dummyCleaningServiceWithPayment(null));
    }

    @Test
    void should_not_create_when_type_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningServiceTestData.dummyCleaningServiceWithType(null));
    }

    @Test
    void should_not_create_when_total_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningServiceTestData.dummyCleaningServiceWithTotal(null));
    }

    @Test
    void should_not_create_when_time_estimation_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningServiceTestData.dummyCleaningServiceWithTimeEstimation(null));
    }
}
