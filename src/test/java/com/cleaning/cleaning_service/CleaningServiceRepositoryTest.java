package com.cleaning.cleaning_service;

import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.cleaning_service.details.*;
import com.cleaning.infrastructure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.junit.jupiter.*;

import static java.util.Collections.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CleaningServiceRepositoryTest {

    @Autowired
    private CleaningServiceRepository repository;

    @Test
    public void create_cleaning_service() {
        CleaningService cleaningService = new CleaningService(
                new ContactInfo("firstName", "lastName", "email", "phoneNumber"),
                new Location("county", "city", "address"),
                new StandardCleaningDetails("200", Parking.FREE, HomeAccess.CALL, 5, 3, 2),
                null,
                emptyList(),
                emptyList(),
                Frequency.ONE_TIME, Payment.CARD, CleaningType.STANDARD, 100.0, 3
        );

        CleaningService savedCleaningService = repository.save(cleaningService);
        Assertions.assertEquals(savedCleaningService.getContactInfo().getEmail(), "email");
        Assertions.assertEquals(savedCleaningService.getCleaningDetails().getHomeAccess(), HomeAccess.CALL);
    }
}
