package com.cleaning.facade.mapper;

import com.cleaning.entity.*;
import com.cleaning.facade.dto.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CleaningServiceMapper {
    default CleaningDetails toCleaningDetailsEntity(CleaningDetailsDto cleaningDetailsDto) {
        if (cleaningDetailsDto instanceof StandardCleaningDetailsDto) {
            return toStandardCleaningDetailsEntity((StandardCleaningDetailsDto) cleaningDetailsDto);
        }

        if (cleaningDetailsDto instanceof PostConstructionCleaningDetailsDto) {
            return toPostConstructionCleaningDetailsEntity((PostConstructionCleaningDetailsDto) cleaningDetailsDto);
        }

        if (cleaningDetailsDto instanceof DisinfectionCleaningDetailsDto) {
            return toDisinfectionCleaningDetailsEntity((DisinfectionCleaningDetailsDto) cleaningDetailsDto);
        }

        throw new IllegalArgumentException("Unknown subtype of PetDTO");
    }

    CleaningService toCleaningServiceEntity(CleaningServiceDto cleaningServiceDto);
    ContactInfo toContactInfoEntity(ContactInfoDto contactInfoDto);
    Location toLocationEntity(LocationDto locationDto);
    CleaningDate toCleaningDateEntity(CleaningDateDto cleaningDateDto);
    StandardCleaningDetails toStandardCleaningDetailsEntity(StandardCleaningDetailsDto standardCleaningDetailsDto);
    PostConstructionCleaningDetails toPostConstructionCleaningDetailsEntity(PostConstructionCleaningDetailsDto postConstructionCleaningDetailsDto);
    DisinfectionCleaningDetails toDisinfectionCleaningDetailsEntity(DisinfectionCleaningDetailsDto disinfectionCleaningDetailsDto);
}