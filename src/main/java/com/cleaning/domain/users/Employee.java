package com.cleaning.domain.users;

import com.cleaning.domain.users.job_information.*;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Employee extends User {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "job_information_id")
    private JobInformation jobInformation;

    @NoArgsConstructor
    @Getter
    public static class Builder extends User.Builder<Employee.Builder> {
        private JobInformation jobInformation;

        public Builder withJobInformation(JobInformation jobInformation) {
            this.jobInformation = jobInformation;
            return self();
        }

        @Override
        protected Employee.Builder self() {
            return this;
        }

        @Override
        public Employee build() {
            return new Employee(this);
        }
    }

    private Employee(Employee.Builder builder) {
        super(builder, Role.EMPLOYEE);
        this.jobInformation = builder.jobInformation;
    }
}