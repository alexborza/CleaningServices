package com.cleaning.application;

import com.cleaning.domain.users.*;
import com.cleaning.infrastructure.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import javax.persistence.*;
import java.util.*;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CleaningServiceRepository cleaningServiceRepository;

    public void modifyEmergencyContactInfo(Long userId, EmergencyContactInformationDto dto){

    }

    public void modifyJobInfo(Long userId, JobInformationDto dto){
    }

    public EmployeeDto getEmployee(Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return administratorMapper.toEmployeeDto(employee);
    }

    public List<EmployeesDayAgenda> getEmployeesAgendaForDate(String date, String frequency){
        return null;
    }

    public List<CleaningServiceDto> getEmployeeCleaningServicesForDate(Long id, String date) {
        return null;
    }
}
