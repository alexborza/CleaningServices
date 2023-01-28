package com.cleaning.facade.mapper;

import com.cleaning.entity.cleaning_service.description.*;
import com.cleaning.facade.dto.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CleaningServiceDescriptionMapper {
    CleaningDescription toCleaningServiceDescriptionEntity(CleaningServiceDescriptionDto dto);
    CleaningServiceDescriptionDto toCleaningServiceDescriptionDto(CleaningDescription entity);
}
