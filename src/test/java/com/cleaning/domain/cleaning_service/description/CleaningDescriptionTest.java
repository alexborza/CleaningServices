package com.cleaning.domain.cleaning_service.description;

import com.cleaning.domain.*;
import com.cleaning.domain.cleaning_service.description.*;
import com.cleaning.infrastructure.cleaning_service.cleaning_description.data.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CleaningDescriptionTest {

    @Test
    void should_create_description() {
        CleaningDescription cleaningDescription = CleaningDescriptionTestData.dummyCleaningDescription();

        assertThat(cleaningDescription.getStandardCleaningDescription()).isEqualTo("standard");
        assertThat(cleaningDescription.getDeepCleaningDescription()).isEqualTo("deep");
        assertThat(cleaningDescription.getDisinfectionCleaningDescription()).isEqualTo("disinfection");
        assertThat(cleaningDescription.getPostConstructionCleaningDescription()).isEqualTo("postConstruction");
    }

    @Test
    void should_not_create_description_with_blank_fields() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningDescriptionTestData.dummyCleaningDescription(
                        "",
                        "",
                        "",
                        ""
                ));
    }

    @Test
    void should_not_create_description_with_null_fields() {
        assertThrows(DomainConstraintViolationException.class,
                () -> CleaningDescriptionTestData.dummyCleaningDescription(
                        null,
                        null,
                        null,
                        null
                ));
    }
}
