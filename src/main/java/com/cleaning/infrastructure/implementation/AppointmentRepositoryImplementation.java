package com.cleaning.infrastructure.implementation;

import com.cleaning.domain.appointment.*;
import com.cleaning.infrastructure.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

@Repository
public class AppointmentRepositoryImplementation implements AppointmentRepository {

    @Autowired
    private AppointmentJpaRepository jpaRepository;

    @Override
    public Optional<Appointment> findById(Long id) {

        return jpaRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {

        return jpaRepository.existsById(id);
    }

    @Override
    public List<Appointment> findAllByCleaningDate(String date) {

        return jpaRepository.findAllByCleaningDateOrderByTimeSlotStartingHourAsc(LocalDate.parse(date));
    }

    @Override
    public Appointment save(Appointment appointment) {

        return jpaRepository.save(appointment);
    }

    @Override
    public void saveAll(Iterable<Appointment> appointments) {

        jpaRepository.saveAll(appointments);
    }

    @Override
    public List<Appointment> findAllByEmployeeAndCleaningDate(Long employeeId, String date) {

        return jpaRepository.findAllByEmployeeAndCleaningDate(employeeId, LocalDate.parse(date));
    }

    @Override
    public List<Appointment> findAllByCleaningService(Long cleaningServiceId) {

        return jpaRepository.findAllByCleaningService(cleaningServiceId);
    }

    @Override
    public void updateStatusCompleted(Long id) {

        jpaRepository.updateStatusCompleted(id);
    }

    @Override
    public void deleteById(Long id) {

        jpaRepository.deleteById(id);
    }

    @Override
    public void updateNotCompletedDueAppointments(LocalDate currentDate) {

        jpaRepository.updateNotCompletedDueAppointments(currentDate);
    }
}
