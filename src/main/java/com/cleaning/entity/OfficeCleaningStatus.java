package com.cleaning.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OfficeCleaningStatus {
    @JsonProperty("Sent")
    Sent("Sent"),

    @JsonProperty("Not Sent")
    NotSent("Not Sent"),

    @JsonProperty("Accepted")
    Accepted("Accepted"),

    @JsonProperty("Declined")
    Declined("Declined");

    private final String label;

    OfficeCleaningStatus(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
