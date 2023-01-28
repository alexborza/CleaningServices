package com.cleaning.facade.vo;

import com.cleaning.entity.appointment.*;
import com.cleaning.entity.cleaning_service.*;

public interface CleaningServiceDisplayVO {
    Long getId();
    CleaningType getType();
    String getPhoneNumber();
    String getEmail();
    String getSquareMeters();
    AppointmentStatus getStatus();
}
