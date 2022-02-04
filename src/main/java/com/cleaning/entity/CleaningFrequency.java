package com.cleaning.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CleaningFrequency {
    @JsonProperty("One Time")
    OneTime("One Time",0),

    @JsonProperty("Weekly")
    Weekly("Weekly",15),

    @JsonProperty("BiWeekly")
    BiWeekly("BiWeekly",10),

    @JsonProperty("Monthly")
    Monthly("Monthly",5);

    private final String label;
    private final int discount;

    CleaningFrequency(String label, int discount){
        this.label = label;
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    public String getLabel() {
        return label;
    }
}
