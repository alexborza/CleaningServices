package com.cleaning.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CleaningServiceType {
    @JsonProperty("Standard Cleaning")
    StandardCleaning("Standard Cleaning"),

    @JsonProperty("Deep Cleaning")
    DeepCleaning("Deep Cleaning"),

    @JsonProperty("Disinfection Cleaning")
    DisinfectionCleaning("Disinfection Cleaning"),

    @JsonProperty("Post Construction Cleaning")
    PostContructionCleaning("Post Construction Cleaning");

    private final String label;

    CleaningServiceType(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
