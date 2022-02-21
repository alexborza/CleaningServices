package com.cleaning.entity;

import lombok.*;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
public class JobInformation {
    private String title;
    private String supervisor;
    private String workPhone;

    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;
    private String hiringDate;
    private Long salary;
}
