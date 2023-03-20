package com.cleaning.exposition;

import com.cleaning.application.*;
import com.cleaning.domain.appointment.*;
import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.cleaning_service.description.*;
import com.cleaning.domain.cleaning_service.details.*;
import com.cleaning.domain.cleaning_service.prices.*;
import com.cleaning.exposition.representation.data.*;
import com.cleaning.exposition.representation.request.appointment.*;
import com.cleaning.exposition.representation.request.cleaning_service.*;
import com.cleaning.exposition.representation.response.appointment.*;
import com.cleaning.exposition.representation.response.cleaning_service.*;
import com.cleaning.exposition.representation.response.cleaning_service.description.*;
import com.cleaning.exposition.representation.response.cleaning_service.details.*;
import com.cleaning.exposition.representation.response.cleaning_service.prices.*;
import com.cleaning.domain.appointment.data.*;
import com.cleaning.infrastructure.cleaning_service.cleaning_description.data.*;
import com.cleaning.infrastructure.cleaning_service.cleaning_price.data.*;
import com.cleaning.domain.cleaning_service.data.*;
import com.cleaning.domain.users.data.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;

import java.time.*;
import java.util.*;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CleaningServiceControllerTest {

    @InjectMocks
    private CleaningServiceController cleaningServiceController;

    @Mock
    private CleaningServiceService cleaningServiceService;

    @Test
    public void testFindClientsCleaningService() {

        Long userId = 1L;
        List<TimeSlotRepresentation> timeSlotRepresentationts = List.of(
                new TimeSlotRepresentation(8, 10),
                new TimeSlotRepresentation(10, 12),
                new TimeSlotRepresentation(13, 15)
        );
        List<CleaningServiceMinimalView> views = timeSlotRepresentationts.stream()
                .map(this::toView)
                .collect(toList());

        when(cleaningServiceService.findClientsCleaningServices(userId)).thenReturn(views);

        ResponseEntity<List<CleaningServiceMinimalRepresentation>> response = cleaningServiceController.findClientsCleaningServices(userId);
        assertThat(response).isNotNull();

        List<CleaningServiceMinimalRepresentation> body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.stream().map(CleaningServiceMinimalRepresentation::getTimeSlotRepresentation).map(TimeSlotRepresentation::getStartingHour).collect(toList()))
                .containsExactly(8, 10, 13);
        assertThat(body.stream().map(CleaningServiceMinimalRepresentation::getTimeSlotRepresentation).map(TimeSlotRepresentation::getFinishingHour).collect(toList()))
                .containsExactly(10, 12, 15);
    }

    @Test
    public void testGetCleaningService() {
        Long id = 1L;
        CleaningService cleaningService = CleaningServiceTestData.dummyCleaningService(UserTestData.dummyClient("username", "email"));
        Appointment dummyAppointment = AppointmentTestData.dummyAppointment(cleaningService, UserTestData.dummyEmployee("EU", "EE"), new TimeSlot(8, 10), LocalDate.of(2023, 2, 27), AppointmentStatus.ACTIVE);
        List<Appointment> appointments = Collections.singletonList(dummyAppointment);

        when(cleaningServiceService.getCleaningService(id)).thenReturn(cleaningService);
        when(cleaningServiceService.findCleaningServiceAppointments(id)).thenReturn(appointments);

        ResponseEntity<CleaningServiceRepresentation> response = cleaningServiceController.getCleaningService(id);
        assertThat(response).isNotNull();

        CleaningServiceRepresentation body = response.getBody();
        assertThat(body).isNotNull();

        List<AppointmentRepresentation> appointmentRepresentations = body.getAppointments();
        StandardCleaningDetailsRepresentation cleaningDetailsRepresentation = (StandardCleaningDetailsRepresentation) body.getCleaningDetails();
        StandardCleaningDetails cleaningDetails = (StandardCleaningDetails) cleaningService.getCleaningDetails();

        assertThat(appointmentRepresentations).hasSize(1);
        assertThat(body.getTimeEstimation()).isEqualTo(cleaningService.getTimeEstimation());
        assertThat(body.getTotal()).isEqualTo(cleaningService.getTotal());
        assertThat(body.getPayment()).isEqualTo(cleaningService.getPayment());
        assertThat(body.getFrequency()).isEqualTo(cleaningService.getFrequency());

        assertThat(cleaningDetailsRepresentation.getHomeAccess()).isEqualTo(cleaningDetails.getHomeAccess());
        assertThat(cleaningDetailsRepresentation.getSquareMeters()).isEqualTo(cleaningDetails.getSquareMeters());
        assertThat(cleaningDetailsRepresentation.getParking()).isEqualTo(cleaningDetails.getParking());
        assertThat(cleaningDetailsRepresentation.getBathrooms()).isEqualTo(cleaningDetails.getBathrooms());
        assertThat(cleaningDetailsRepresentation.getBedrooms()).isEqualTo(cleaningDetails.getBedrooms());
        assertThat(cleaningDetailsRepresentation.getKitchens()).isEqualTo(cleaningDetails.getKitchens());

        AppointmentRepresentation appointmentRepresentation = appointmentRepresentations.get(0);
        assertThat(appointmentRepresentation.getTimeSlot().getStartingHour()).isEqualTo(dummyAppointment.getTimeSlot().getStartingHour());
        assertThat(appointmentRepresentation.getTimeSlot().getFinishingHour()).isEqualTo(dummyAppointment.getTimeSlot().getEndingHour());
        assertThat(appointmentRepresentation.getCleaningDate()).isEqualTo(LocalDate.of(2023, 2, 27).toString());
    }

    @Test
    public void testCreateCleaningService() {

        List<AppointmentCreation> appointmentCreations = List.of(
                AppointmentCreationTestData.dummyAppointmentCreation(new TimeSlotRepresentation(8, 10)),
                AppointmentCreationTestData.dummyAppointmentCreation(new TimeSlotRepresentation(10, 14))
        );

        CleaningServiceCreation cleaningServiceCreation = CleaningServiceCreationTestData.dummyCleaningServiceCreation(appointmentCreations);

        ResponseEntity<Void> response = cleaningServiceController.createCleaningService(2L, cleaningServiceCreation);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(cleaningServiceService)
                .createCleaningService(any(), any(), any());
    }

    @Test
    public void testAddMessageToCleaningService() {

        MessageCreation messageCreation = new MessageCreation("sender", "content");

        ResponseEntity<Void> response = cleaningServiceController.addMessageToCleaningService(1L, messageCreation);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(cleaningServiceService).addMessageToCleaningService(any(), any());
    }

    @Test
    public void testGetDescriptions() {
        CleaningDescription cleaningDescription = CleaningDescriptionTestData.dummyCleaningDescription();

        when(cleaningServiceService.getDescriptions()).thenReturn(Optional.ofNullable(cleaningDescription));

        ResponseEntity<CleaningDescriptionRepresentation> response = cleaningServiceController.getCleaningDescriptions();
        assertThat(response).isNotNull();

        CleaningDescriptionRepresentation body = response.getBody();
        assertThat(body).isNotNull();
        assert cleaningDescription != null;
        assertThat(body.getStandardCleaningDescription()).isEqualTo(cleaningDescription.getStandardCleaningDescription());
        assertThat(body.getDeepCleaningDescription()).isEqualTo(cleaningDescription.getDeepCleaningDescription());
        assertThat(body.getDisinfectionCleaningDescription()).isEqualTo(cleaningDescription.getDisinfectionCleaningDescription());
        assertThat(body.getPostConstructionCleaningDescription()).isEqualTo(cleaningDescription.getPostConstructionCleaningDescription());
    }

    @Test
    public void testGetEmptyInstanceDescriptions() {

        CleaningDescriptionRepresentation emptyInstance = CleaningDescriptionRepresentation.emptyInstance();

        when(cleaningServiceService.getDescriptions()).thenReturn(Optional.empty());

        ResponseEntity<CleaningDescriptionRepresentation> response = cleaningServiceController.getCleaningDescriptions();
        assertThat(response).isNotNull();

        CleaningDescriptionRepresentation body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getStandardCleaningDescription()).isEqualTo(emptyInstance.getStandardCleaningDescription());
        assertThat(body.getDeepCleaningDescription()).isEqualTo(emptyInstance.getDeepCleaningDescription());
        assertThat(body.getDisinfectionCleaningDescription()).isEqualTo(emptyInstance.getDisinfectionCleaningDescription());
        assertThat(body.getPostConstructionCleaningDescription()).isEqualTo(emptyInstance.getPostConstructionCleaningDescription());
    }

    @Test
    public void testGetCleaningServicePrices() {
        CleaningPrice cleaningPrice = CleaningPriceTestData.dummyCleaningPrice();

        when(cleaningServiceService.getCleaningServicePrices()).thenReturn(Optional.ofNullable(cleaningPrice));

        ResponseEntity<CleaningPricesRepresentation> response = cleaningServiceController.getCleaningPrices();
        assertThat(response).isNotNull();

        CleaningPricesRepresentation body = response.getBody();
        assertThat(body).isNotNull();
        assert cleaningPrice != null;

        StandardCleaningPricesRepresentation standardCleaningPricesRepresentation = body.getStandardCleaningPrices();
        StandardCleaningPrice standardCleaningPrice = cleaningPrice.getStandardCleaningPrice();
        DeepCleaningPricesRepresentation deepCleaningPricesRepresentation = body.getDeepCleaningPrices();
        DeepCleaningPrice deepCleaningPrice = cleaningPrice.getDeepCleaningPrice();
        DisinfectionCleaningPricesRepresentation disinfectionCleaningPricesRepresentation = body.getDisinfectionCleaningPrices();
        DisinfectionCleaningPrice disinfectionCleaningPrice = cleaningPrice.getDisinfectionCleaningPrice();
        PostConstructionCleaningPricesRepresentation postConstructionCleaningPricesRepresentation = body.getPostConstructionCleaningPrices();
        PostConstructionCleaningPrice postConstructionCleaningPrice = cleaningPrice.getPostConstructionCleaningPrice();

        assertThat(body.getPaidParkingSpotPrice()).isEqualTo(cleaningPrice.getPaidParkingSpotPrice());
        assertThat(body.getPickUpKeysPrice()).isEqualTo(cleaningPrice.getPickUpKeysPrice());
        assertThat(standardCleaningPricesRepresentation.getStandardServicePrice()).isEqualTo(standardCleaningPrice.getStandardServicePrice());
        assertThat(standardCleaningPricesRepresentation.getStandardServiceBathroom()).isEqualTo(standardCleaningPrice.getStandardServiceBathroom());
        assertThat(standardCleaningPricesRepresentation.getStandardServiceBedroom()).isEqualTo(standardCleaningPrice.getStandardServiceBedroom());
        assertThat(standardCleaningPricesRepresentation.getStandardServiceKitchen()).isEqualTo(standardCleaningPrice.getStandardServiceKitchen());
        assertThat(deepCleaningPricesRepresentation.getDeepServicePrice()).isEqualTo(deepCleaningPrice.getDeepServicePrice());
        assertThat(deepCleaningPricesRepresentation.getDeepServiceBathroom()).isEqualTo(deepCleaningPrice.getDeepServiceBathroom());
        assertThat(deepCleaningPricesRepresentation.getDeepServiceBedroom()).isEqualTo(deepCleaningPrice.getDeepServiceBedroom());
        assertThat(deepCleaningPricesRepresentation.getDeepServiceKitchen()).isEqualTo(deepCleaningPrice.getDeepServiceKitchen());
        assertThat(disinfectionCleaningPricesRepresentation.getDisinfectionServicePrice()).isEqualTo(disinfectionCleaningPrice.getDisinfectionServicePrice());
        assertThat(postConstructionCleaningPricesRepresentation.getPostConstructionServicePrice()).isEqualTo(postConstructionCleaningPrice.getPostConstructionServicePrice());
        assertThat(postConstructionCleaningPricesRepresentation.getRoomPrice()).isEqualTo(postConstructionCleaningPrice.getRoomPrice());
    }

    @Test
    public void testGetEmptyInstanceCleaningServicePrices() {
        CleaningPricesRepresentation emptyInstance = CleaningPricesRepresentation.emptyInstance();

        when(cleaningServiceService.getCleaningServicePrices()).thenReturn(Optional.empty());

        ResponseEntity<CleaningPricesRepresentation> response = cleaningServiceController.getCleaningPrices();
        assertThat(response).isNotNull();

        CleaningPricesRepresentation body = response.getBody();
        assertThat(body).isNotNull();

        StandardCleaningPricesRepresentation standardCleaningPricesRepresentation = body.getStandardCleaningPrices();
        StandardCleaningPricesRepresentation standardCleaningPrice = emptyInstance.getStandardCleaningPrices();
        DeepCleaningPricesRepresentation deepCleaningPricesRepresentation = body.getDeepCleaningPrices();
        DeepCleaningPricesRepresentation deepCleaningPrice = emptyInstance.getDeepCleaningPrices();
        DisinfectionCleaningPricesRepresentation disinfectionCleaningPricesRepresentation = body.getDisinfectionCleaningPrices();
        DisinfectionCleaningPricesRepresentation disinfectionCleaningPrice = emptyInstance.getDisinfectionCleaningPrices();
        PostConstructionCleaningPricesRepresentation postConstructionCleaningPricesRepresentation = body.getPostConstructionCleaningPrices();
        PostConstructionCleaningPricesRepresentation postConstructionCleaningPrice = emptyInstance.getPostConstructionCleaningPrices();

        assertThat(body.getPaidParkingSpotPrice()).isEqualTo(emptyInstance.getPaidParkingSpotPrice());
        assertThat(body.getPickUpKeysPrice()).isEqualTo(emptyInstance.getPickUpKeysPrice());
        assertThat(standardCleaningPricesRepresentation.getStandardServicePrice()).isEqualTo(standardCleaningPrice.getStandardServicePrice());
        assertThat(standardCleaningPricesRepresentation.getStandardServiceBathroom()).isEqualTo(standardCleaningPrice.getStandardServiceBathroom());
        assertThat(standardCleaningPricesRepresentation.getStandardServiceBedroom()).isEqualTo(standardCleaningPrice.getStandardServiceBedroom());
        assertThat(standardCleaningPricesRepresentation.getStandardServiceKitchen()).isEqualTo(standardCleaningPrice.getStandardServiceKitchen());
        assertThat(deepCleaningPricesRepresentation.getDeepServicePrice()).isEqualTo(deepCleaningPrice.getDeepServicePrice());
        assertThat(deepCleaningPricesRepresentation.getDeepServiceBathroom()).isEqualTo(deepCleaningPrice.getDeepServiceBathroom());
        assertThat(deepCleaningPricesRepresentation.getDeepServiceBedroom()).isEqualTo(deepCleaningPrice.getDeepServiceBedroom());
        assertThat(deepCleaningPricesRepresentation.getDeepServiceKitchen()).isEqualTo(deepCleaningPrice.getDeepServiceKitchen());
        assertThat(disinfectionCleaningPricesRepresentation.getDisinfectionServicePrice()).isEqualTo(disinfectionCleaningPrice.getDisinfectionServicePrice());
        assertThat(postConstructionCleaningPricesRepresentation.getPostConstructionServicePrice()).isEqualTo(postConstructionCleaningPrice.getPostConstructionServicePrice());
        assertThat(postConstructionCleaningPricesRepresentation.getRoomPrice()).isEqualTo(postConstructionCleaningPrice.getRoomPrice());
    }

    private CleaningServiceMinimalView toView(TimeSlotRepresentation timeSlotRepresentation) {
        return new CleaningServiceMinimalView() {
            @Override
            public Long getId() {
                return 1L;
            }

            @Override
            public CleaningType getCleaningType() {
                return CleaningType.STANDARD;
            }

            @Override
            public Double getTotal() {
                return 200.0;
            }

            @Override
            public Integer getTimeEstimation() {
                return 2;
            }

            @Override
            public LocalDate getNextCleaningDate() {
                return LocalDate.now();
            }

            @Override
            public Integer getStartingHour() {
                return timeSlotRepresentation.getStartingHour();
            }

            @Override
            public Integer getEndingHour() {
                return timeSlotRepresentation.getFinishingHour();
            }
        };
    }

}
