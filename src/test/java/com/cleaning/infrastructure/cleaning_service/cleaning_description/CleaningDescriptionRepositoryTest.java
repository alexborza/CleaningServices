package com.cleaning.infrastructure.cleaning_service.cleaning_description;

import com.cleaning.domain.cleaning_service.description.*;
import com.cleaning.infrastructure.cleaning_service.cleaning_description.data.*;
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
public class CleaningDescriptionRepositoryTest {

    @Autowired
    private CleaningDescriptionRepositoryImplementation repositoryImplementation;

    @Test
    public void testFindTopByOrderByIdDescIsEmpty() {
        Optional<CleaningDescription> topByOrderByIdDesc = repositoryImplementation.findTopByOrderByIdDesc();

        assertThat(topByOrderByIdDesc).isEmpty();
    }

    @Test
    @DirtiesContext
    public void testFindTopByOrderByIdDesc() {
        populateData();
        Optional<CleaningDescription> topByOrderByIdDesc = repositoryImplementation.findTopByOrderByIdDesc();

        assertThat(topByOrderByIdDesc).isNotEmpty();
        CleaningDescription cleaningDescription = topByOrderByIdDesc.get();
        assertThat(cleaningDescription.getStandardCleaningDescription()).isEqualTo("standard3");
        assertThat(cleaningDescription.getDeepCleaningDescription()).isEqualTo("deep3");
        assertThat(cleaningDescription.getPostConstructionCleaningDescription()).isEqualTo("post3");
        assertThat(cleaningDescription.getDisinfectionCleaningDescription()).isEqualTo("disinfection3");
    }

    private void populateData() {
        CleaningDescription cd1 = CleaningDescriptionTestData.dummyCleaningDescription("standard1", "deep1", "post1", "disinfection1");
        CleaningDescription cd2 = CleaningDescriptionTestData.dummyCleaningDescription("standard2", "deep2", "post2", "disinfection2");
        CleaningDescription cd3 = CleaningDescriptionTestData.dummyCleaningDescription("standard3", "deep3", "post3", "disinfection3");

        repositoryImplementation.save(cd1);
        repositoryImplementation.save(cd2);
        repositoryImplementation.save(cd3);
    }
}
