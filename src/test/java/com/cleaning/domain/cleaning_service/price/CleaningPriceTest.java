package com.cleaning.domain.cleaning_service.price;

import com.cleaning.domain.*;
import com.cleaning.domain.cleaning_service.prices.*;
import com.cleaning.infrastructure.cleaning_service.cleaning_price.data.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CleaningPriceTest {

    @Test
    void should_create_cleaning_price() {
        CleaningPrice cleaningPrice = CleaningPriceTestData.dummyCleaningPrice();

        assertThat(cleaningPrice.getStandardCleaningPrice().getStandardServicePrice()).isEqualTo(10.0);
        assertThat(cleaningPrice.getStandardCleaningPrice().getStandardServiceKitchen()).isEqualTo(10.0);
        assertThat(cleaningPrice.getStandardCleaningPrice().getStandardServiceBedroom()).isEqualTo(10.0);
        assertThat(cleaningPrice.getStandardCleaningPrice().getStandardServiceBathroom()).isEqualTo(10.0);

        assertThat(cleaningPrice.getDeepCleaningPrice().getDeepServicePrice()).isEqualTo(10.0);
        assertThat(cleaningPrice.getDeepCleaningPrice().getDeepServiceKitchen()).isEqualTo(10.0);
        assertThat(cleaningPrice.getDeepCleaningPrice().getDeepServiceBedroom()).isEqualTo(10.0);
        assertThat(cleaningPrice.getDeepCleaningPrice().getDeepServiceBathroom()).isEqualTo(10.0);

        assertThat(cleaningPrice.getDisinfectionCleaningPrice().getDisinfectionServicePrice()).isEqualTo(10.0);

        assertThat(cleaningPrice.getPostConstructionCleaningPrice().getPostConstructionServicePrice()).isEqualTo(10.0);
        assertThat(cleaningPrice.getPostConstructionCleaningPrice().getRoomPrice()).isEqualTo(10.0);

        assertThat(cleaningPrice.getPickUpKeysPrice()).isEqualTo(10.0);
        assertThat(cleaningPrice.getPaidParkingSpotPrice()).isEqualTo(10.0);
    }

    @Test
    void should_not_create_cleaning_prices_when_pick_up_keys_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningPriceTestData.dummyCleaningPriceWithPaidAndPickUp(
                        10.0, null, 10.0, 10.0, 10.0, 10.0));
    }

    @Test
    void should_not_create_cleaning_prices_when_paid_parking_spot_price_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningPriceTestData.dummyCleaningPriceWithPaidAndPickUp(
                        null, 10.0, 10.0, 10.0, 10.0, 10.0));
    }

    @Test
    void should_not_create_cleaning_prices_when_standard_cleaning_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningPriceTestData.dummyCleaningPrice(
                        null,
                        CleaningPriceTestData.dummyDeepCleaningPrice(10.0, 10.0, 10.0, 10.0),
                        CleaningPriceTestData.dummyPostConstructionCleaningPrice(10.0, 10.0),
                        CleaningPriceTestData.dummyDisinfectionCleaningPrice(10.0))
        );
    }

    @Test
    void should_not_create_cleaning_prices_when_deep_cleaning_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningPriceTestData.dummyCleaningPrice(
                        CleaningPriceTestData.dummyStandardCleaningPrice(10.0, 10.0, 10.0, 10.0),
                        null,
                        CleaningPriceTestData.dummyPostConstructionCleaningPrice(10.0, 10.0),
                        CleaningPriceTestData.dummyDisinfectionCleaningPrice(10.0))
        );
    }

    @Test
    void should_not_create_cleaning_prices_when_post_construction_cleaning_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningPriceTestData.dummyCleaningPrice(
                        CleaningPriceTestData.dummyStandardCleaningPrice(10.0, 10.0, 10.0, 10.0),
                        CleaningPriceTestData.dummyDeepCleaningPrice(10.0, 10.0, 10.0, 10.0),
                        null,
                        CleaningPriceTestData.dummyDisinfectionCleaningPrice(10.0))
        );
    }

    @Test
    void should_not_create_cleaning_prices_when_disinfection_cleaning_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningPriceTestData.dummyCleaningPrice(
                        CleaningPriceTestData.dummyStandardCleaningPrice(10.0, 10.0, 10.0, 10.0),
                        CleaningPriceTestData.dummyDeepCleaningPrice(10.0, 10.0, 10.0, 10.0),
                        CleaningPriceTestData.dummyPostConstructionCleaningPrice(10.0, 10.0),
                        null)
        );
    }
}
