package com.cleaning.domain.users;

import lombok.*;

import javax.persistence.*;
import java.time.*;

@Entity
@Table(name = "job_information")
@NoArgsConstructor
@Getter
public class JobInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String supervisor;
    private String workPhone;
    private LocalDate hiringDate;
    private Long salary;

    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;

    private JobInformation(JobInformationBuilder builder) {
        this.title = builder.getTitle();
        this.supervisor = builder.getSupervisor();
        this.workPhone = builder.getWorkPhone();
        this.hiringDate = builder.getHiringDate();
        this.salary = builder.getSalary();
        this.employmentStatus = builder.getEmploymentStatus();
    }

    @NoArgsConstructor
    @Getter
    public static class JobInformationBuilder {
        private String title;
        private String supervisor;
        private String workPhone;
        private LocalDate hiringDate;
        private Long salary;
        private EmploymentStatus employmentStatus;

        public JobInformationBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public JobInformationBuilder withSupervisor(String supervisor) {
            this.supervisor = supervisor;
            return this;
        }

        public JobInformationBuilder withWorkPhone(String workPhone) {
            this.workPhone = workPhone;
            return this;
        }

        public JobInformationBuilder withHiringDate(LocalDate hiringDate) {
            this.hiringDate = hiringDate;
            return this;
        }

        public JobInformationBuilder withSalary(Long salary) {
            this.salary = salary;
            return this;
        }

        public JobInformationBuilder withEmploymentStatus(EmploymentStatus employmentStatus) {
            this.employmentStatus = employmentStatus;
            return this;
        }

        public JobInformation build() {
            return new JobInformation(this);
        }
    }
}
