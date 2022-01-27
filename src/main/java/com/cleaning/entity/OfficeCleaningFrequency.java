package com.cleaning.entity;

public enum OfficeCleaningFrequency {
    DailyIncludingWeekends("DailyIncludingWeekends"),
    DailyWeekdaysOnly("DailyWeekdaysOnly"),
    TwiceWeek("TwiceWeek"),
    Weekly("Weekly"),
    Every2Weeks("Every2Weeks"),
    Monthly("Monthly"),
    ToBeDetermined("ToBeDetermined");

    private final String label;

    OfficeCleaningFrequency(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
