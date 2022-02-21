package com.cleaning.facade.mapper;

import com.cleaning.entity.*;
import com.cleaning.facade.dto.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AdministratorMapper {
    default User toUserEntity(UserDto dto){
        if(dto instanceof EmployeeDto){
            return toEmployeeEntity((EmployeeDto) dto);
        }
        throw new IllegalArgumentException("Unknown subtype of CleaningDetailsDto");
    }

    Employee toEmployeeEntity(EmployeeDto dto);
}
