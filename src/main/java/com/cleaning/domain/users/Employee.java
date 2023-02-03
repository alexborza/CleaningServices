package com.cleaning.domain.users;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Employee extends User {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_info_id")
    private EmployeeInformation employeeInformation;

    public Employee(String username, String email, String password, UserInformation userInformation, EmployeeInformation employeeInformation) {
        super(username, email, password, userInformation, Role.EMPLOYEE);
        this.employeeInformation = employeeInformation;
    }
}