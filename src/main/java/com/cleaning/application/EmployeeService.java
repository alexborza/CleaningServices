package com.cleaning.application;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.users.*;
import com.cleaning.exposition.representation.response.appointment.*;
import com.cleaning.exposition.representation.response.cleaning_service.*;
import com.cleaning.exposition.representation.response.users.*;
import com.cleaning.infrastructure.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.*;

@Transactional
@Service
public class EmployeeService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private JobInformationRepository jobInformationRepository;

    public void updateJobInformation(Long jobInformationId, JobInformationRepresentation representation){
        if(!jobInformationRepository.existsById(jobInformationId)) {
            throw new EntityNotFoundException("JobInformation not found for id: " + jobInformationId.toString());
        }

        jobInformationRepository.updateJobInformation(jobInformationId, representation.toDomain());
    }

//    public List<EmployeesDayAgenda> getEmployeesAgendaForDate(String date, String frequency){
//        return null;
//    }

    public List<AppointmentRepresentation> getEmployeesAppointmentsForDate(Long id, String date) {

        List<Appointment> appointments = appointmentRepository.findAllByEmployeeAndCleaningDate(id, date);

        return appointments.stream()
                .map(AppointmentRepresentation::fromDomain)
                .collect(Collectors.toList());
    }
}
