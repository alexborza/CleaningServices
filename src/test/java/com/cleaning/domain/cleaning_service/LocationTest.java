package com.cleaning.domain.cleaning_service;

import com.cleaning.domain.*;
import com.cleaning.infrastructure.cleaning_service.data.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocationTest {

    @Test
    void should_create() {
        Location location = LocationTestData.dummyLocation();

        assertThat(location.getCity()).isEqualTo("city");
        assertThat(location.getAddress()).isEqualTo("address");
        assertThat(location.getCounty()).isEqualTo("county");
    }

    @Test
    void should_not_create_when_county_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> LocationTestData.dummyLocation(null, "c", "a"));
    }

    @Test
    void should_not_create_when_county_blank() {
        assertThrows(DomainConstraintViolationException.class,
                () -> LocationTestData.dummyLocation("", "c", "a"));
    }

    @Test
    void should_not_create_when_city_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> LocationTestData.dummyLocation("c", null, "a"));
    }

    @Test
    void should_not_create_when_city_blank() {
        assertThrows(DomainConstraintViolationException.class,
                () -> LocationTestData.dummyLocation("c", "", "a"));
    }

    @Test
    void should_not_create_when_address_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> LocationTestData.dummyLocation("c", "c", null));
    }

    @Test
    void should_not_create_when_address_blank() {
        assertThrows(DomainConstraintViolationException.class,
                () -> LocationTestData.dummyLocation("c", "c", ""));
    }
}
