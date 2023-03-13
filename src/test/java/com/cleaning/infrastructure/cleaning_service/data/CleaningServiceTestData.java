package com.cleaning.infrastructure.cleaning_service.data;

import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.cleaning_service.details.*;
import com.cleaning.domain.users.*;

import java.time.*;
import java.util.*;

public class CleaningServiceTestData {

    public static CleaningService dummyCleaningService(Client client){
        return new CleaningServiceBuilder()
                .withContactInfo(dummyContactInfo())
                .withLocation(dummyLocation())
                .withCleaningDetails(dummyStandardCleaningDetails())
                .withClient(client)
                .withFrequency(Frequency.ONE_TIME)
                .withPayment(Payment.CARD)
                .withMessages(dummyMessages())
                .withType(CleaningType.STANDARD)
                .withTotal(100.0)
                .withTimeEstimation(3)
                .build();

    }

    public static ContactInfo dummyContactInfo() {
        return new ContactInfo("firstName", "lastName", "email", "phoneNumber");
    }

    public static Location dummyLocation() {
        return new Location("county", "city", "address");
    }

    public static CleaningDetails dummyStandardCleaningDetails() {
        return new StandardCleaningDetails("200", Parking.FREE, HomeAccess.CALL, 5, 3, 2);
    }

    public static List<Message> dummyMessages() {
        return List.of(dummyMessage("message1"), dummyMessage("message2"));
    }

    public static Message dummyMessage(String message) {
        return new Message(LocalDateTime.now(), "sender", message);
    }
}
