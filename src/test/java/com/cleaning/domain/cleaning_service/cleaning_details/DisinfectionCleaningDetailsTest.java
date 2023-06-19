package com.cleaning.domain.cleaning_service.cleaning_details;

import com.cleaning.domain.*;
import com.cleaning.domain.cleaning_service.details.*;
import com.cleaning.domain.cleaning_service.data.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class DisinfectionCleaningDetailsTest {

    @Test
    void should_create() {
        DisinfectionCleaningDetails disinfectionCleaningDetails = CleaningDetailsTestData.dummyDisinfectionCleaningDetails();

        assertThat(disinfectionCleaningDetails.getSquareMeters()).isEqualTo("200.0");
        assertThat(disinfectionCleaningDetails.getHomeAccess()).isEqualTo(HomeAccess.CALL);
        assertThat(disinfectionCleaningDetails.getParking()).isEqualTo(Parking.FREE);
        assertThat(disinfectionCleaningDetails.getProperty()).isEqualTo(Property.HOUSE);

    }

    @Test
    void should_not_create_when_property_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningDetailsTestData.dummyDisinfectionCleaningDetails(null));
    }

    @Test
    void should_not_create_when_square_meters_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningDetailsTestData.dummyDisinfectionCleaningDetails(null, Parking.FREE, HomeAccess.CALL));
    }

    @Test
    void should_not_create_when_parking_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningDetailsTestData.dummyDisinfectionCleaningDetails("200.0", null, HomeAccess.CALL));
    }

    @Test
    void should_not_create_when_home_access_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningDetailsTestData.dummyDisinfectionCleaningDetails("200.0", Parking.FREE, null));
    }
}
