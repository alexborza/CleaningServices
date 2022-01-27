package com.cleaning.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class SpaceType {
    private int enclosedOffices;
    private int workStations;
    private int meetingRooms;
    private int bathrooms;
    private int toilets;
    private int hallways;
    private int cafeterias;
}
