package com.cleaning.application;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.users.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CleaningServiceRepository cleaningServiceRepository;

    public void completeAppointment(Long id) {
        if(!appointmentRepository.existsById(id)) {
            throw new AppointmentNotFoundException(id);
        }

        appointmentRepository.updateStatusCompleted(id);
    }

    public void cancelAppointment(Long id) {
        if(!appointmentRepository.existsById(id)) {
            throw new AppointmentNotFoundException(id);
        }

        appointmentRepository.deleteById(id);
    }

    public void addAppointment(Long cleaningServiceId, Long employeeId, AppointmentCommand appointmentCommand) {

        Employee employee = (Employee) userRepository.findById(employeeId)
                .orElseThrow(() -> new UserNotFoundException(employeeId));

        CleaningService cleaningService = cleaningServiceRepository.findById(cleaningServiceId)
                .orElseThrow(() -> new CleaningServiceNotFoundException(cleaningServiceId));

        Appointment appointment = appointmentCommand.toDomain(cleaningService, employee);
        appointmentRepository.save(appointment);
    }
}
