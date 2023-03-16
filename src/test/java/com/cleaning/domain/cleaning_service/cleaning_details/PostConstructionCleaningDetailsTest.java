package com.cleaning.domain.cleaning_service.cleaning_details;

import com.cleaning.domain.*;
import com.cleaning.domain.cleaning_service.details.*;
import com.cleaning.domain.cleaning_service.data.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class PostConstructionCleaningDetailsTest {

    @Test
    void should_create() {
        PostConstructionCleaningDetails postConstructionCleaningDetails = CleaningDetailsTestData.dummyPostConstructionCleaningDetails();

        assertThat(postConstructionCleaningDetails.getSquareMeters()).isEqualTo(200.0);
        assertThat(postConstructionCleaningDetails.getHomeAccess()).isEqualTo(HomeAccess.CALL);
        assertThat(postConstructionCleaningDetails.getParking()).isEqualTo(Parking.FREE);
        assertThat(postConstructionCleaningDetails.getRooms()).isEqualTo(3);
        assertThat(postConstructionCleaningDetails.getProperty()).isEqualTo(Property.HOUSE);
    }

    @Test
    void should_not_create_when_property_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningDetailsTestData.dummyPostConstructionCleaningDetails(null, 3));
    }

    @Test
    void should_not_create_when_rooms_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningDetailsTestData.dummyPostConstructionCleaningDetails(Property.HOUSE, null));
    }

    @Test
    void should_not_create_when_rooms_out_of_range() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningDetailsTestData.dummyPostConstructionCleaningDetails(Property.HOUSE, 0));
    }
}
