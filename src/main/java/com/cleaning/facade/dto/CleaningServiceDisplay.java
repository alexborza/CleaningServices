package com.cleaning.facade.dto;

import com.cleaning.entity.*;
import lombok.*;

@Data
public class CleaningServiceDisplay {
    private Long id;
    private CleaningServiceType type;
    private String phoneNumber;
    private String email;
    private String squareMeters;
    private String status;
}
