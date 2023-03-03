package com.cleaning.exposition.representation.request.cleaning_service;

import com.cleaning.domain.cleaning_service.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class LocationCreation {
    private final String county;
    private final String city;
    private final String address;

    public Location toDomain() {
        return new Location(
                county,
                city,
                address
        );
    }
}
