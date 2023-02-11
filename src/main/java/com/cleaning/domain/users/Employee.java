package com.cleaning.domain.users;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Employee extends User {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_info_id")
    private EmployeeInformation employeeInformation;

    @NoArgsConstructor
    @Getter
    public static class Builder extends User.Builder<Employee.Builder> {
        private EmployeeInformation employeeInformation;

        public Builder withEmployeeInformation(EmployeeInformation employeeInformation) {
            this.employeeInformation = employeeInformation;
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
        this.employeeInformation = builder.getEmployeeInformation();
    }
}