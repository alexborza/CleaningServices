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

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<CleaningService> cleaningServices = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_info_id", referencedColumnName = "id")
    private EmployeeInformation employeeInformation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agenda_id", referencedColumnName = "id")
    private Agenda agenda;

    public void addCleaningService(CleaningService cleaningService){
        this.cleaningServices.add(cleaningService);
    }
    
}
