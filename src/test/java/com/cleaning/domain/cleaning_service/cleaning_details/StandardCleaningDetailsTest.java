package com.cleaning.domain.cleaning_service.cleaning_details;

import com.cleaning.domain.*;
import com.cleaning.domain.cleaning_service.details.*;
import com.cleaning.domain.cleaning_service.data.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StandardCleaningDetailsTest {

    @Test
    void should_create() {
        StandardCleaningDetails standardCleaningDetails = CleaningDetailsTestData.dummyStandardCleaningDetails();

        assertThat(standardCleaningDetails.getSquareMeters()).isEqualTo(200.0);
        assertThat(standardCleaningDetails.getHomeAccess()).isEqualTo(HomeAccess.CALL);
        assertThat(standardCleaningDetails.getParking()).isEqualTo(Parking.FREE);
        assertThat(standardCleaningDetails.getBedrooms()).isEqualTo(5);
        assertThat(standardCleaningDetails.getBathrooms()).isEqualTo(3);
        assertThat(standardCleaningDetails.getKitchens()).isEqualTo(2);
    }

    @Test
    void should_not_create_when_bedrooms_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningDetailsTestData.dummyStandardCleaningDetails(null, 3, 2));
    }

    @Test
    void should_not_create_when_bedrooms_out_of_range() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningDetailsTestData.dummyStandardCleaningDetails(0, 3, 2));
    }

    @Test
    void should_not_create_when_bathrooms_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningDetailsTestData.dummyStandardCleaningDetails(5, null, 2));
    }

    @Test
    void should_not_create_when_bathrooms_out_of_range() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningDetailsTestData.dummyStandardCleaningDetails(5, 0, 2));
    }

    @Test
    void should_not_create_when_kitchens_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningDetailsTestData.dummyStandardCleaningDetails(5, 3, null));
    }

    @Test
    void should_not_create_when_kitchens_out_of_range() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningDetailsTestData.dummyStandardCleaningDetails(5, 3, 0));
    }
}
