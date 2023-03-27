package com.cleaning.application;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.appointment.exception.*;
import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.cleaning_service.exception.*;
import com.cleaning.domain.users.*;
import com.cleaning.domain.users.exception.*;
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

    public void rescheduleAppointment(Long id, Long cleaningServiceId, AppointmentCommand appointmentCommand) {
        //Delete the existing appointment
        if(!appointmentRepository.existsById(id)) {
            throw new AppointmentNotFoundException(id);
        }

        appointmentRepository.deleteById(id);

        //Create another appointment for CleaningService
        Employee employee = (Employee) userRepository.findById(appointmentCommand.getEmployeeId())
                .orElseThrow(() -> new UserNotFoundException(appointmentCommand.getEmployeeId()));

        CleaningService cleaningService = cleaningServiceRepository.findById(cleaningServiceId)
                .orElseThrow(() -> new CleaningServiceNotFoundException(cleaningServiceId));

        Appointment appointment = appointmentCommand.toDomain(cleaningService, employee);
        appointmentRepository.save(appointment);
    }
}
