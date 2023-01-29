package com.cleaning.domain.users;

import com.cleaning.domain.appointment.*;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor
public class Employee extends User {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_info_id")
    private EmployeeInformation employeeInformation;

    @OneToMany(mappedBy = "employee")
    private List<Appointment> appointments;

    public Employee(String username, String email, String password, UserInformation userInformation, Role role, EmployeeInformation employeeInformation, List<Appointment> appointments) {
        super(username, email, password, userInformation, role);
        this.employeeInformation = employeeInformation;
        this.appointments = appointments;
    }
}
