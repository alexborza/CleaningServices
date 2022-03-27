package com.cleaning.facade.mapper;

import com.cleaning.entity.*;
import com.cleaning.facade.dto.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CleaningServiceDescriptionMapper {
    CleaningServiceDescription toCleaningServiceDescriptionEntity(CleaningServiceDescriptionDto dto);
    CleaningServiceDescriptionDto toCleaningServiceDescriptionDto(CleaningServiceDescription entity);
}
