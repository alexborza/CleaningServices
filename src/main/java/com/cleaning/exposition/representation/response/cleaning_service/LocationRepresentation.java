package com.cleaning.exposition.representation.response.cleaning_service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LocationRepresentation {
    private final String county;
    private final String city;
    private final String address;
}
