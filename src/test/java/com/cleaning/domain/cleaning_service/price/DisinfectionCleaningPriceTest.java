package com.cleaning.domain.cleaning_service.price;

import com.cleaning.domain.*;
import com.cleaning.domain.cleaning_service.prices.*;
import com.cleaning.infrastructure.cleaning_service.cleaning_price.data.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DisinfectionCleaningPriceTest {

    @Test
    void should_create() {
        DisinfectionCleaningPrice disinfectionCleaningPrice = CleaningPriceTestData.dummyDisinfectionCleaningPrice(10.0);

        assertThat(disinfectionCleaningPrice.getDisinfectionServicePrice()).isEqualTo(10.0);
    }

    @Test
    void should_not_create_when_price_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningPriceTestData.dummyDisinfectionCleaningPrice(null));
    }
}
