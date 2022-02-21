package com.cleaning.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EmployeeInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private JobInformation jobInformation;

    @Embedded
    private EmergencyContactInformation emergencyContactInformation;
}
