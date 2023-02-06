package com.cleaning.domain.users;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "employee_information")
@NoArgsConstructor
@Getter
public class EmployeeInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private JobInformation jobInformation;

    public EmployeeInformation(JobInformation jobInformation) {
        this.jobInformation = jobInformation;
    }
}
