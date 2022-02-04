package com.cleaning.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DayTimeOfficeCleaning {

    @JsonProperty("During Regular Business Hours")
    DuringRegularBusinessHours("DuringRegularBusinessHours"),

    @JsonProperty("After Regular Business Hours")
    AfterRegularBusinessHours("AfterRegularBusinessHours"),

    @JsonProperty("Graveyard Shift")
    GraveyardShift("GraveyardShift");

    private final String label;

    DayTimeOfficeCleaning(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
