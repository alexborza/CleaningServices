package com.cleaning.facade.dto;


import com.cleaning.entity.appointment.*;
import com.cleaning.entity.cleaning_service.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CleaningServiceDto {
    private final Long id;
    private final ContactInfoDto contactInfo;
    private final LocationDto location;
    private final CleaningDetailsDto cleaningDetails;
    private final Frequency cleaningFrequency;
    private final CleaningDateDto cleaningDate;
    private final Payment paymentMethod;
    private final double total;
    private final int timeEstimation;
    private final CleaningType type;
    private final AppointmentStatus status;
}
