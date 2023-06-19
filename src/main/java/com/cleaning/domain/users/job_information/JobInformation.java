package com.cleaning.domain.users.job_information;

import com.cleaning.domain.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.*;
import java.util.*;

@Entity
@Table(name = "job_information")
@NoArgsConstructor
@Getter
public class JobInformation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String workPhone;

    @NotNull
    private LocalDate hiringDate;

    @NotNull
    private Long salary;

    private JobInformation(JobInformationBuilder builder) {
        this.id = builder.id;
        this.workPhone = builder.workPhone;
        this.hiringDate = builder.hiringDate;
        this.salary = builder.salary;
        validate(this);
    }

    @NoArgsConstructor
    public static class JobInformationBuilder {
        private Long id;
        private String workPhone;
        private LocalDate hiringDate;
        private Long salary;

        public JobInformationBuilder withId(Long id) {
            this.id = id;
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

        public JobInformation build() {
            return new JobInformation(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobInformation that = (JobInformation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
