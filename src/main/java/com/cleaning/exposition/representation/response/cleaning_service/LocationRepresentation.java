package com.cleaning.exposition.representation.response.cleaning_service;

import com.cleaning.domain.cleaning_service.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LocationRepresentation {
    private final String county;
    private final String city;
    private final String address;

    public static LocationRepresentation fromDomain(Location location) {
        return new LocationRepresentation(
                location.getCounty(),
                location.getCity(),
                location.getAddress()
        );
    }

    public Location toDomain() {
        return new Location(
                county,
                city,
                address
        );
    }
}
