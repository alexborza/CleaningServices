package com.cleaning.application;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.users.*;
import com.cleaning.exposition.representation.response.appointment.*;
import com.cleaning.exposition.representation.response.shared.*;
import com.cleaning.exposition.representation.response.users.*;
import com.cleaning.utility.*;
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

    @Autowired
    private UserRepository userRepository;

    public void updateJobInformation(Long jobInformationId, JobInformationRepresentation representation){
        if(!jobInformationRepository.existsById(jobInformationId)) {
            throw new EntityNotFoundException("JobInformation not found for id: " + jobInformationId.toString());
        }

        jobInformationRepository.updateJobInformation(jobInformationId, representation.toDomain());
    }

    public Set<EmployeeAvailableIntervals> getEmployeesAvailableIntervalsForDate(String date, Integer timeEstimation){
        List<Appointment> appointments = appointmentRepository.findAllByCleaningDate(date);
        List<Long> employeeIds = userRepository.findAllEmployeeIds();

        Map<Long, Set<TimeSlot>> employeesAvailableIntervals =
                EmployeeUtils.calculateEmployeesAvailableIntervals(employeeIds, appointments, timeEstimation);

        Set<EmployeeAvailableIntervals> availableIntervals = new TreeSet<>();

        for(Map.Entry<Long, Set<TimeSlot>> entry: employeesAvailableIntervals.entrySet()){
            Long employeeId = entry.getKey();
            Set<TimeSlot> timeSlots = entry.getValue();

            List<EmployeeAvailableIntervals> employeeAvailableIntervals = timeSlots.stream()
                    .map(timeSlot -> new EmployeeAvailableIntervals(employeeId, TimeSlotRepresentation.fromDomain(timeSlot)))
                    .collect(Collectors.toList());
            availableIntervals.addAll(employeeAvailableIntervals);
        }

        return availableIntervals;
    }

    public List<AppointmentRepresentation> getEmployeesAppointmentsForDate(Long id, String date) {

        List<Appointment> appointments = appointmentRepository.findAllByEmployeeAndCleaningDate(id, date);

        return appointments.stream()
                .map(AppointmentRepresentation::fromDomain)
                .collect(Collectors.toList());
    }
}
