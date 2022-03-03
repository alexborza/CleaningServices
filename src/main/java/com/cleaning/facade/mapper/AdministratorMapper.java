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
    UserInformation userInformationDtoToUserInformation(UserInformationDto userInformationDto);
    EmergencyContactInformation emergencyContactInfoDtoToEntity(EmergencyContactInformationDto dto);
    Employee toEmployeeEntity(EmployeeDto dto);
    default UserDto toUserDto(User entity){
        if(entity instanceof Employee){
            return toEmployeeDto((Employee) entity);
        } else {
            return toClientDto(entity);
        }
    }

    EmployeeDto toEmployeeDto(Employee entity);
    UserDto toClientDto(User entity);
}
