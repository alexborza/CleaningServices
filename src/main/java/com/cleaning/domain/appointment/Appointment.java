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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cleaning_service_id")
    private CleaningService cleaningService;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private LocalDate cleaningDate;

    @Embedded
    private TimeSlot timeSlot;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    public Appointment(CleaningService cleaningService, Employee employee, LocalDate cleaningDate, TimeSlot timeSlot, AppointmentStatus status) {
        this.cleaningService = cleaningService;
        this.employee = employee;
        this.cleaningDate = cleaningDate;
        this.timeSlot = timeSlot;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return cleaningDate.isEqual(that.cleaningDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cleaningDate);
    }
}
