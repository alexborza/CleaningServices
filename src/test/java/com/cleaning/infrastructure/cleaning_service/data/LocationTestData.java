package com.cleaning.infrastructure.cleaning_service.data;

import com.cleaning.domain.cleaning_service.*;

public class LocationTestData {

    public static Location dummyLocation() {
        return dummyLocation("county", "city", "address");
    }

    public static Location dummyLocation(String county, String city, String address) {
        return new Location(county, city, address);
    }
}
