package com.cleaning.entity;

public enum CleaningFrequency {
    OneTime("One Time",0),
    Weekly("Weekly",15),
    BiWeekly("BiWeekly",10),
    Monthly("Monthly",5);

    private final String label;
    private final int discount;

    CleaningFrequency(String label, int discount){
        this.label = label;
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    public String getLabel() {
        return label;
    }
}
