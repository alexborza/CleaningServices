package com.cleaning.infrastructure.implementation;

import com.cleaning.domain.appointment.*;
import com.cleaning.exposition.representation.response.users.*;
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
    public List<Appointment> findAllByCleaningDate(String date) {

        return jpaRepository.findAllByCleaningDate(LocalDate.parse(date));
    }

    @Override
    public void saveAll(Iterable<Appointment> appointments) {

        jpaRepository.saveAll(appointments);
    }

    @Override
    public List<Appointment> findAllByEmployeeAndCleaningDate(Long employeeId, String date) {

        return jpaRepository.findAllByEmployeeAndCleaningDate(employeeId, LocalDate.parse(date));
    }
}
