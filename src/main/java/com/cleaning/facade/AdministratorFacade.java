package com.cleaning.facade;

import com.cleaning.entity.*;
import com.cleaning.facade.dto.*;
import com.cleaning.facade.dto.response.*;
import com.cleaning.facade.mapper.*;
import com.cleaning.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class AdministratorFacade {

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AdministratorMapper mapper;

    @Autowired
    private PasswordEncoder encoder;

    public ResponseEntity<MessageResponse> createEmployeeContract(UserDto dto){
        if (userRepository.existsByUsername(dto.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Username is already taken!"));
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email is already in use!"));
        }
        encodePassword(dto);
        User employee = mapper.toUserEntity(dto);
        setEmployeeRole(employee);
        userRepository.save(employee);
        return ResponseEntity.ok(new MessageResponse("Employee registered successfully!"));
    }

    public List<EmployeeDto> getAllEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(mapper::toEmployeeDto)
                .collect(Collectors.toList());
    }

    private void encodePassword(UserDto dto){
        dto.setPassword(encoder.encode(dto.getPassword()));
    }

    private void setEmployeeRole(User employee){
        employee.setRoles(Set.of(this.getEmployeeRole(ERole.ROLE_EMPLOYEE)));
    }

    private Role getEmployeeRole(ERole role){
        return roleRepository.findByName(role)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    }
}
