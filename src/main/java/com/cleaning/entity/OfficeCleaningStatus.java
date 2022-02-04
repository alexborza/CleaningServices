package com.cleaning.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OfficeCleaningStatus {
    @JsonProperty("Sent")
    Sent("Sent"),

    @JsonProperty("Not Sent")
    NotSent("Not Sent");

    private final String label;

    OfficeCleaningStatus(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
