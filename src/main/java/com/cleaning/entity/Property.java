package com.cleaning.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Property {
    @JsonProperty("Apartment")
    Apartment("Apartment"),

    @JsonProperty("Family Home")
    FamilyHome("Family Home"),

    @JsonProperty("Office Space")
    OfficeSpace("Office Space");

    private final String label;

    Property(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
