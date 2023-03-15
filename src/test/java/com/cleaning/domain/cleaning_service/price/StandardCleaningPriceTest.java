package com.cleaning.domain.cleaning_service.price;

import com.cleaning.domain.*;
import com.cleaning.domain.cleaning_service.prices.*;
import com.cleaning.infrastructure.cleaning_service.cleaning_price.data.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StandardCleaningPriceTest {

    @Test
    void should_create() {
        StandardCleaningPrice standardCleaningPrice = CleaningPriceTestData.dummyStandardCleaningPrice(
                10.0,
                10.0,
                10.0,
                10.0);

        assertThat(standardCleaningPrice.getStandardServicePrice()).isEqualTo(10.0);
        assertThat(standardCleaningPrice.getStandardServiceBathroom()).isEqualTo(10.0);
        assertThat(standardCleaningPrice.getStandardServiceBedroom()).isEqualTo(10.0);
        assertThat(standardCleaningPrice.getStandardServiceKitchen()).isEqualTo(10.0);
    }

    @Test
    void should_not_create_when_price_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningPriceTestData.dummyStandardCleaningPrice(
                        null,
                        10.0,
                        10.0,
                        10.0)
        );
    }

    @Test
    void should_not_create_when_kitchen_price_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningPriceTestData.dummyStandardCleaningPrice(
                        10.0,
                        null,
                        10.0,
                        10.0)
        );
    }

    @Test
    void should_not_create_when_bedroom_price_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningPriceTestData.dummyStandardCleaningPrice(
                        10.0,
                        10.0,
                        null,
                        10.0)
        );
    }

    @Test
    void should_not_create_when_bathroom_price_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningPriceTestData.dummyStandardCleaningPrice(
                        10.0,
                        10.0,
                        10.0,
                        null)
        );
    }
}
