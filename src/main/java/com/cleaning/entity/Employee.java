package com.cleaning.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee extends User {

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

    public List<CleaningService> getFrequentCleaningServicesForDate(String date, String frequency){
        return cleaningServices.stream()
                .filter(cs -> cs.isDateOneOfTheNextCleaningDates(date, frequency))
                .collect(Collectors.toList());
    }
    
}
