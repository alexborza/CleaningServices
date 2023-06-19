package com.cleaning.infrastructure.cleaning_service.cleaning_price;

import com.cleaning.domain.cleaning_service.prices.*;
import com.cleaning.infrastructure.cleaning_service.cleaning_price.data.*;
import com.cleaning.infrastructure.implementation.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.annotation.*;
import org.springframework.test.context.junit.jupiter.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CleaningPriceRepositoryTest {

    @Autowired
    private CleaningPriceRepositoryImplementation repositoryImplementation;

    @Test
    public void testFindTopByOrderByIdDescIsEmpty() {
        Optional<CleaningPrice> topByOrderByIdDesc = repositoryImplementation.findTopByOrderByIdDesc();

        assertThat(topByOrderByIdDesc).isEmpty();
    }

    @Test
    @DirtiesContext
    public void testFindTopByOrderByIdDesc() {
        Double price = 20.0;
        populateData();
        Optional<CleaningPrice> topByOrderByIdDesc = repositoryImplementation.findTopByOrderByIdDesc();

        assertThat(topByOrderByIdDesc).isNotEmpty();
        CleaningPrice cleaningPrice = topByOrderByIdDesc.get();
        StandardCleaningPrice standardCleaningPrice = cleaningPrice.getStandardCleaningPrice();
        DeepCleaningPrice deepCleaningPrice = cleaningPrice.getDeepCleaningPrice();
        PostConstructionCleaningPrice postConstructionCleaningPrice = cleaningPrice.getPostConstructionCleaningPrice();
        DisinfectionCleaningPrice disinfectionCleaningPrice = cleaningPrice.getDisinfectionCleaningPrice();

        assertThat(standardCleaningPrice.getStandardServicePrice()).isEqualTo(price);
        assertThat(standardCleaningPrice.getStandardServiceBathroom()).isEqualTo(price);
        assertThat(standardCleaningPrice.getStandardServiceBedroom()).isEqualTo(price);
        assertThat(standardCleaningPrice.getStandardServiceKitchen()).isEqualTo(price);

        assertThat(deepCleaningPrice.getDeepServicePrice()).isEqualTo(price);
        assertThat(deepCleaningPrice.getDeepServiceBathroom()).isEqualTo(price);
        assertThat(deepCleaningPrice.getDeepServiceBedroom()).isEqualTo(price);
        assertThat(deepCleaningPrice.getDeepServiceKitchen()).isEqualTo(price);

        assertThat(postConstructionCleaningPrice.getPostConstructionServicePrice()).isEqualTo(price);
        assertThat(postConstructionCleaningPrice.getRoomPrice()).isEqualTo(price);

        assertThat(disinfectionCleaningPrice.getDisinfectionServicePrice()).isEqualTo(price);
    }

    private void populateData() {
        CleaningPrice cp1 = CleaningPriceTestData.dummyCleaningPrice(12.0, 13.0, 14.0, 15.0);
        CleaningPrice cp2 = CleaningPriceTestData.dummyCleaningPrice(15.0, 15.0, 15.0, 15.0);
        CleaningPrice cp3 = CleaningPriceTestData.dummyCleaningPrice(20.0, 20.0, 20.0, 20.0);

        repositoryImplementation.save(cp1);
        repositoryImplementation.save(cp2);
        repositoryImplementation.save(cp3);
    }
}
