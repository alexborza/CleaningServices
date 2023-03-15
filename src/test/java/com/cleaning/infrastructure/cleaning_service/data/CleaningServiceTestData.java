package com.cleaning.infrastructure.cleaning_service.data;

import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.cleaning_service.details.*;
import com.cleaning.domain.users.*;

public class CleaningServiceTestData {

    public static CleaningService dummyCleaningService(Client client){
        return new CleaningServiceBuilder()
                .withContactInfo(ContactInfoTestData.dummyContactInfo())
                .withLocation(LocationTestData.dummyLocation())
                .withCleaningDetails(CleaningDetailsTestData.dummyStandardCleaningDetails())
                .withClient(client)
                .withFrequency(Frequency.ONE_TIME)
                .withPayment(Payment.CARD)
                .withMessages(MessageTestData.dummyMessages())
                .withType(CleaningType.STANDARD)
                .withTotal(100.0)
                .withTimeEstimation(3)
                .build();

    }

    public static CleaningService dummyCleaningServiceWithContactInfo(
            ContactInfo contactInfo){

        return new CleaningServiceBuilder()
                .withContactInfo(contactInfo)
                .withLocation(LocationTestData.dummyLocation())
                .withCleaningDetails(CleaningDetailsTestData.dummyStandardCleaningDetails())
                .withClient(null)
                .withFrequency(Frequency.ONE_TIME)
                .withPayment(Payment.CARD)
                .withMessages(MessageTestData.dummyMessages())
                .withType(CleaningType.STANDARD)
                .withTotal(100.0)
                .withTimeEstimation(3)
                .build();
    }

    public static CleaningService dummyCleaningServiceWithLocation(
            Location location){

        return new CleaningServiceBuilder()
                .withContactInfo(ContactInfoTestData.dummyContactInfo())
                .withLocation(location)
                .withCleaningDetails(CleaningDetailsTestData.dummyStandardCleaningDetails())
                .withClient(null)
                .withFrequency(Frequency.ONE_TIME)
                .withPayment(Payment.CARD)
                .withMessages(MessageTestData.dummyMessages())
                .withType(CleaningType.STANDARD)
                .withTotal(100.0)
                .withTimeEstimation(3)
                .build();
    }

    public static CleaningService dummyCleaningServiceWithCleaningDetails(
            CleaningDetails cleaningDetails){

        return new CleaningServiceBuilder()
                .withContactInfo(ContactInfoTestData.dummyContactInfo())
                .withLocation(LocationTestData.dummyLocation())
                .withCleaningDetails(cleaningDetails)
                .withClient(null)
                .withFrequency(Frequency.ONE_TIME)
                .withPayment(Payment.CARD)
                .withMessages(MessageTestData.dummyMessages())
                .withType(CleaningType.STANDARD)
                .withTotal(100.0)
                .withTimeEstimation(3)
                .build();
    }

    public static CleaningService dummyCleaningServiceWithFrequency(
            Frequency frequency){

        return new CleaningServiceBuilder()
                .withContactInfo(ContactInfoTestData.dummyContactInfo())
                .withLocation(LocationTestData.dummyLocation())
                .withCleaningDetails(CleaningDetailsTestData.dummyStandardCleaningDetails())
                .withClient(null)
                .withFrequency(frequency)
                .withPayment(Payment.CARD)
                .withMessages(MessageTestData.dummyMessages())
                .withType(CleaningType.STANDARD)
                .withTotal(100.0)
                .withTimeEstimation(3)
                .build();
    }

    public static CleaningService dummyCleaningServiceWithPayment(
            Payment payment){

        return new CleaningServiceBuilder()
                .withContactInfo(ContactInfoTestData.dummyContactInfo())
                .withLocation(LocationTestData.dummyLocation())
                .withCleaningDetails(CleaningDetailsTestData.dummyStandardCleaningDetails())
                .withClient(null)
                .withFrequency(Frequency.ONE_TIME)
                .withPayment(payment)
                .withMessages(MessageTestData.dummyMessages())
                .withType(CleaningType.STANDARD)
                .withTotal(100.0)
                .withTimeEstimation(3)
                .build();
    }

    public static CleaningService dummyCleaningServiceWithType(
            CleaningType cleaningType){

        return new CleaningServiceBuilder()
                .withContactInfo(ContactInfoTestData.dummyContactInfo())
                .withLocation(LocationTestData.dummyLocation())
                .withCleaningDetails(CleaningDetailsTestData.dummyStandardCleaningDetails())
                .withClient(null)
                .withFrequency(Frequency.ONE_TIME)
                .withPayment(Payment.CARD)
                .withMessages(MessageTestData.dummyMessages())
                .withType(cleaningType)
                .withTotal(100.0)
                .withTimeEstimation(3)
                .build();
    }

    public static CleaningService dummyCleaningServiceWithTotal(
            Double total){

        return new CleaningServiceBuilder()
                .withContactInfo(ContactInfoTestData.dummyContactInfo())
                .withLocation(LocationTestData.dummyLocation())
                .withCleaningDetails(CleaningDetailsTestData.dummyStandardCleaningDetails())
                .withClient(null)
                .withFrequency(Frequency.ONE_TIME)
                .withPayment(Payment.CARD)
                .withMessages(MessageTestData.dummyMessages())
                .withType(CleaningType.STANDARD)
                .withTotal(total)
                .withTimeEstimation(3)
                .build();
    }

    public static CleaningService dummyCleaningServiceWithTimeEstimation(
            Integer timeEstimation){

        return new CleaningServiceBuilder()
                .withContactInfo(ContactInfoTestData.dummyContactInfo())
                .withLocation(LocationTestData.dummyLocation())
                .withCleaningDetails(CleaningDetailsTestData.dummyStandardCleaningDetails())
                .withClient(null)
                .withFrequency(Frequency.ONE_TIME)
                .withPayment(Payment.CARD)
                .withMessages(MessageTestData.dummyMessages())
                .withType(CleaningType.STANDARD)
                .withTotal(100.0)
                .withTimeEstimation(timeEstimation)
                .build();
    }
}
