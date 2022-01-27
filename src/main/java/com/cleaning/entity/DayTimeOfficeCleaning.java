package com.cleaning.entity;

public enum DayTimeOfficeCleaning {
    DuringRegularBusinessHours("DuringRegularBusinessHours"),
    AfterRegularBusinessHours("AfterRegularBusinessHours"),
    GraveyardShift("GraveyardShift");

    private final String label;

    DayTimeOfficeCleaning(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
