package com.cleaning.facade.dto;

import com.cleaning.entity.appointment.*;
import com.cleaning.entity.cleaning_service.*;
import lombok.*;

@Data
public class CleaningServiceDisplay {
    private Long id;
    private CleaningType type;
    private String phoneNumber;
    private String email;
    private String squareMeters;
    private AppointmentStatus status;
}
