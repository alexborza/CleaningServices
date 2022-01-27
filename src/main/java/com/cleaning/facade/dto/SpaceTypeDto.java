package com.cleaning.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SpaceTypeDto {
    private final int enclosedOffices;
    private final int workStations;
    private final int meetingRooms;
    private final int bathrooms;
    private final int toilets;
    private final int hallways;
    private final int cafeterias;
}
