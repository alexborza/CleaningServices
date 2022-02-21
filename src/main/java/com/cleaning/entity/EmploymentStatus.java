package com.cleaning.entity;

import com.fasterxml.jackson.annotation.*;

public enum EmploymentStatus {
    @JsonProperty("Part Time")
    PartTime("Part Time"),

    @JsonProperty("Full Time")
    FullTime("Full Time");

    private final String label;

    EmploymentStatus(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
