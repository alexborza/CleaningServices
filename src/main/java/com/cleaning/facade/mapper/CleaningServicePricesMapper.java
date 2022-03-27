package com.cleaning.facade.mapper;

import com.cleaning.entity.*;
import com.cleaning.facade.dto.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CleaningServicePricesMapper {
    CleaningServicePrices toCleaningServicePricesEntity(CleaningServicePricesDto dto);
    CleaningServicePricesDto toCleaningServicePricesDto(CleaningServicePrices entity);
}
