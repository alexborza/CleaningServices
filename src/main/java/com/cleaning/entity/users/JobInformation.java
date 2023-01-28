package com.cleaning.entity.users;

import lombok.*;

import javax.persistence.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class JobInformation {

    private String title;
    private String supervisor;
    private String workPhone;
    private String hiringDate;
    private Long salary;

    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;
}
