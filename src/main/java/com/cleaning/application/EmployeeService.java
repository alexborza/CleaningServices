package com.cleaning.application;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.users.*;
import com.cleaning.utility.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import javax.persistence.*;
import java.util.*;

@Transactional
@Service
public class EmployeeService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private JobInformationRepository jobInformationRepository;

    @Autowired
    private UserRepository userRepository;

    public void updateJobInformation(Long jobInformationId, JobInformation jobInformation){
        if(!jobInformationRepository.existsById(jobInformationId)) {
            throw new EntityNotFoundException("JobInformation not found for id: " + jobInformationId.toString());
        }

        jobInformationRepository.updateJobInformation(jobInformationId, jobInformation);
    }

    public Map<Long, Set<TimeSlot>> getEmployeesAvailableIntervalsForDate(String date, Integer timeEstimation){
        List<Appointment> appointments = appointmentRepository.findAllByCleaningDate(date);
        List<Long> employeeIds = userRepository.findAllEmployeeIds();

        return EmployeeUtils.calculateEmployeesAvailableIntervals(employeeIds, appointments, timeEstimation);
    }

    public List<Appointment> getEmployeesAppointmentsForDate(Long id, String date) {

        return appointmentRepository.findAllByEmployeeAndCleaningDate(id, date);
    }
}
