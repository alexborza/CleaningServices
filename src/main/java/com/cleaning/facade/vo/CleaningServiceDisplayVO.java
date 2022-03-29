package com.cleaning.facade.vo;

import com.cleaning.entity.*;

public interface CleaningServiceDisplayVO {
    Long getId();
    CleaningServiceType getType();
    String getPhoneNumber();
    String getEmail();
    String getSquareMeters();
    String getStatus();
}
