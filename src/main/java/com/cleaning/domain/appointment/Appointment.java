package com.cleaning.domain.appointment;

import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.users.*;
import lombok.*;

import javax.persistence.*;
import java.time.*;
import java.util.*;

@Entity
@Table(name = "appointment")
@NoArgsConstructor
@Getter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cleaning_service_id")
    private CleaningService cleaningService;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private LocalDate cleaningDate;

    @Embedded
    private TimeSlot timeSlot;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @NoArgsConstructor
    @Getter
    public static class AppointmentBuilder {

        private CleaningService cleaningService;
        private Employee employee;
        private LocalDate cleaningDate;
        private TimeSlot timeSlot;
        private AppointmentStatus status;

        public AppointmentBuilder withCleaningService(CleaningService cleaningService) {

            this.cleaningService = cleaningService;
            return this;
        }

        public AppointmentBuilder withEmployee(Employee employee) {

            this.employee = employee;
            return this;
        }

        public AppointmentBuilder withCleaningDate(LocalDate cleaningDate) {

            this.cleaningDate = cleaningDate;
            return this;
        }

        public AppointmentBuilder withTimeSlot(TimeSlot timeSlot) {

            this.timeSlot = timeSlot;
            return this;
        }

        public AppointmentBuilder withStatus(AppointmentStatus status) {

            this.status = status;
            return this;
        }

        public Appointment build() {

            return new Appointment(this);
        }
    }

    public Appointment(AppointmentBuilder builder) {
        this.cleaningService = builder.getCleaningService();
        this.employee = builder.getEmployee();
        this.cleaningDate = builder.getCleaningDate();
        this.timeSlot = builder.getTimeSlot();
        this.status = builder.getStatus();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(id, that.id) && Objects.equals(cleaningDate, that.cleaningDate) && Objects.equals(timeSlot, that.timeSlot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cleaningDate, timeSlot);
    }
}
