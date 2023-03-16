package com.cleaning.domain.cleaning_service.data;

import com.cleaning.domain.cleaning_service.details.*;

public class CleaningDetailsTestData {

    public static StandardCleaningDetails dummyStandardCleaningDetails() {
        return new StandardCleaningDetails(200.0, Parking.FREE, HomeAccess.CALL, 5, 3, 2);
    }

    public static StandardCleaningDetails dummyStandardCleaningDetails(Integer bedrooms, Integer bathrooms, Integer kitchens) {
        return new StandardCleaningDetails(200.0, Parking.FREE, HomeAccess.CALL, bedrooms, bathrooms, kitchens);
    }

    public static PostConstructionCleaningDetails dummyPostConstructionCleaningDetails() {
        return new PostConstructionCleaningDetails(200.0, Parking.FREE, HomeAccess.CALL, Property.HOUSE, 3);
    }

    public static PostConstructionCleaningDetails dummyPostConstructionCleaningDetails(Property property, Integer rooms) {
        return new PostConstructionCleaningDetails(200.0, Parking.FREE, HomeAccess.CALL, property, rooms);
    }

    public static DisinfectionCleaningDetails dummyDisinfectionCleaningDetails() {
        return new DisinfectionCleaningDetails(200.0, Parking.FREE, HomeAccess.CALL, Property.HOUSE);
    }

    public static DisinfectionCleaningDetails dummyDisinfectionCleaningDetails(Double squareMeters, Parking parking, HomeAccess homeAccess) {
        return new DisinfectionCleaningDetails(squareMeters, parking, homeAccess, Property.HOUSE);
    }

    public static DisinfectionCleaningDetails dummyDisinfectionCleaningDetails(Property property) {
        return new DisinfectionCleaningDetails(200.0, Parking.FREE, HomeAccess.CALL, property);
    }
}
