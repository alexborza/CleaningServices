package com.cleaning.entity;

public enum Property {
    Apartment("Apartment"),
    FamilyHome("Family Home"),
    OfficeSpace("Office Space");

    private final String label;

    Property(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
