package com.cleaning.exposition.mapper;

import com.cleaning.domain.users.*;
import com.cleaning.exposition.representation.response.users.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    default User toUserDomain(UserRepresentation representation){
        if(representation instanceof EmployeeRepresentation){
            return toEmployeeDomain((EmployeeRepresentation) representation);
        } else if(representation instanceof ClientRepresentation){
            return toClientDomain((ClientRepresentation) representation);
        }

        throw new IllegalArgumentException("Unknown subtype of CleaningDetailsDto");
    }

    Client toClientDomain(ClientRepresentation representation);
    Employee toEmployeeDomain(EmployeeRepresentation representation);
}
