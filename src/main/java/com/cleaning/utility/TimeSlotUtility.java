package com.cleaning.utility;

import java.time.*;

public class TimeSlotUtility {

    public static String getTimeSlotInterval(Integer startingHour, Integer endingHour) {
        return LocalTime.of(startingHour, 0).toString() + " - " + LocalTime.of(endingHour, 0).toString();
    }
}
