package com.cleaning.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PrimaryUseOfSpace {
    @JsonProperty("Office Space")
    OfficeSpace("Office Space"),

    @JsonProperty("Office And Some Industrial")
    OfficeAndSomeIndustrial("Office And Some Industrial"),

    @JsonProperty("Primarily Industrial")
    PrimarilyIndustrial("Primarily Industrial");

    private final String label;

    PrimaryUseOfSpace(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
