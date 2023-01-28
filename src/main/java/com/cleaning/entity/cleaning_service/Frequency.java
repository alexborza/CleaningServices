package com.cleaning.entity.cleaning_service;

public enum Frequency {
    ONE_TIME(1, 0),
    TWO_TIME(2, 5),
    FOUR_TIME(4, 10),
    SIX_TIME(6, 15);

    private final Integer numberOfCleanings;
    private final Integer discount;

    Frequency(Integer numberOfCleanings, Integer discount) {
        this.numberOfCleanings = numberOfCleanings;
        this.discount = discount;
    }

    public Integer getNumberOfCleanings() {
        return numberOfCleanings;
    }

    public Integer getDiscount() {
        return discount;
    }
}
