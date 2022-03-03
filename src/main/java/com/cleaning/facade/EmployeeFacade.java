package com.cleaning.facade;

import com.cleaning.entity.*;
import com.cleaning.facade.dto.*;
import com.cleaning.facade.mapper.*;
import com.cleaning.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import javax.persistence.*;
import java.util.*;

@Service
public class EmployeeFacade {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AdministratorMapper administratorMapper;

    public void modifyEmergencyContactInfo(Long userId, EmergencyContactInformationDto dto){
        Employee employee = employeeRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
        EmployeeInformation employeeInformation = employee.getEmployeeInformation();
        employeeInformation.setEmergencyContactInformation(administratorMapper.emergencyContactInfoDtoToEntity(dto));
        employeeRepository.save(employee);
    }
}
