package com.cleaning.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CleaningStatus {
    @JsonProperty("Deleted")
    Deleted("Deleted"),

    @JsonProperty("In progress")
    InProgress("In progress"),

    @JsonProperty("Finished")
    Finished("Finished");

    private final String label;

    CleaningStatus(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
