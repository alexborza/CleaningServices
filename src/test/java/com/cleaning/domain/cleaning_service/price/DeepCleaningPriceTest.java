package com.cleaning.domain.cleaning_service.price;

import com.cleaning.domain.*;
import com.cleaning.domain.cleaning_service.prices.*;
import com.cleaning.infrastructure.cleaning_service.cleaning_price.data.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeepCleaningPriceTest {

    @Test
    void should_create() {
        DeepCleaningPrice deepCleaningPrice = CleaningPriceTestData.dummyDeepCleaningPrice(
                10.0,
                10.0,
                10.0,
                10.0);

        assertThat(deepCleaningPrice.getDeepServicePrice()).isEqualTo(10.0);
        assertThat(deepCleaningPrice.getDeepServiceBathroom()).isEqualTo(10.0);
        assertThat(deepCleaningPrice.getDeepServiceBedroom()).isEqualTo(10.0);
        assertThat(deepCleaningPrice.getDeepServiceKitchen()).isEqualTo(10.0);
    }

    @Test
    void should_not_create_when_price_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningPriceTestData.dummyDeepCleaningPrice(
                        null,
                        10.0,
                        10.0,
                        10.0)
        );
    }

    @Test
    void should_not_create_when_kitchen_price_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningPriceTestData.dummyDeepCleaningPrice(
                        10.0,
                        null,
                        10.0,
                        10.0)
        );
    }

    @Test
    void should_not_create_when_bedroom_price_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningPriceTestData.dummyDeepCleaningPrice(
                        10.0,
                        10.0,
                        null,
                        10.0)
        );
    }

    @Test
    void should_not_create_when_bathroom_price_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningPriceTestData.dummyDeepCleaningPrice(
                        10.0,
                        10.0,
                        10.0,
                        null)
        );
    }
}
