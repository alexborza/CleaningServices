package com.cleaning.facade.dto;


import com.cleaning.entity.CleaningFrequency;
import com.cleaning.entity.CleaningServiceType;
import com.cleaning.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CleaningServiceDto {
    private final Long id;
    private final ContactInfoDto contactInfo;
    private final LocationDto location;
    private final CleaningDetailsDto cleaningDetails;
    private final CleaningFrequency cleaningFrequency;
    private final CleaningDateDto cleaningDate;
    private final PaymentMethod paymentMethod;
    private final double total;
    private final CleaningServiceType type;
}
