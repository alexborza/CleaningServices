package com.cleaning.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OfficeCleaningFrequency {
    @JsonProperty("Daily Including Weekends")
    DailyIncludingWeekends("Daily Including Weekends"),

    @JsonProperty("Daily Weekdays Only")
    DailyWeekdaysOnly("Daily Weekdays Only"),

    @JsonProperty("Twice Week")
    TwiceWeek("Twice Week"),

    @JsonProperty("Weekly")
    Weekly("Weekly"),

    @JsonProperty("Every 2 Weeks")
    Every2Weeks("Every 2 Weeks"),

    @JsonProperty("Monthly")
    Monthly("Monthly"),

    @JsonProperty("To Be Determined")
    ToBeDetermined("To Be Determined");

    private final String label;

    OfficeCleaningFrequency(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
