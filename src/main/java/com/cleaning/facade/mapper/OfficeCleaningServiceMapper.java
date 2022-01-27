package com.cleaning.facade.mapper;

import com.cleaning.entity.*;
import com.cleaning.facade.dto.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfficeCleaningServiceMapper {
    OfficeCleaning toOfficeCleaningEntity(OfficeCleaningDto officeCleaningDto);
    SpaceDetails toSpaceDetailsEntity(SpaceDetailsDto spaceDetailsDto);
    SpaceType toSpaceTypeEntity(SpaceTypeDto spaceTypeDto);
    ContactInfo toContactInfoEntity(ContactInfoDto contactInfoDto);
    Location toLocationEntity(LocationDto locationDto);
}
