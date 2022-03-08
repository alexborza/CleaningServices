package com.cleaning.facade;

import com.cleaning.entity.*;
import com.cleaning.facade.dto.*;
import com.cleaning.facade.mapper.*;
import com.cleaning.facade.vo.*;
import com.cleaning.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.*;

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

    public void modifyJobInfo(Long userId, JobInformationDto dto){
        Employee employee = employeeRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
        EmployeeInformation employeeInformation = employee.getEmployeeInformation();
        employeeInformation.setJobInformation(administratorMapper.jobInformationDtoToEntity(dto));
        employeeRepository.save(employee);
    }

    public EmployeeDto getEmployee(Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return administratorMapper.toEmployeeDto(employee);
    }

    public long getTotalNumberOfEmployees(){
        return employeeRepository.count();
    }

    public List<EmployeesDayAgenda> getEmployeesAgendaForDate(String date){
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(e -> toEmployeeDayAgenda(e, date))
                .collect(Collectors.toList());
    }

    private EmployeesDayAgenda toEmployeeDayAgenda(Employee employee, String date){
        if(employee.getAgenda() == null){
            return createEmptyDayAgenda(employee);
        }
        Agenda agenda = employee.getAgenda();
        Optional<Day> day = getDayForDate(agenda, date);
        return createDayAgenda(employee, day);
    }

    private Optional<Day> getDayForDate(Agenda agenda, String date){
        return agenda.getDays().stream()
                .filter(d -> d.getDate().equals(date))
                .findFirst();
    }

    private EmployeesDayAgenda createDayAgenda(Employee employee, Optional<Day> day){
        if(day.isEmpty()){
            return createEmptyDayAgenda(employee);
        }
        return new EmployeesDayAgenda(employee.getId(), day.get().getAvailableIntervals());
    }

    private EmployeesDayAgenda createEmptyDayAgenda(Employee employee){
        return new EmployeesDayAgenda(employee.getId(), new ArrayList<>(List.of(new AvailableInterval(9, 17))));
    }
}
