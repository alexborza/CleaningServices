package com.cleaning.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee extends User {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "employees_office_cleaning",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "office_cleaning_id"))
    private Set<OfficeCleaning> officeCleanings = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "employees_cleaning_services",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "cleaning_service_id"))
    private Set<OfficeCleaning> cleaningServices = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_info_id", referencedColumnName = "id")
    private EmployeeInformation employeeInformation;
}
