package com.cleaning.application;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.cleaning_service.description.*;
import com.cleaning.domain.cleaning_service.prices.*;
import com.cleaning.domain.users.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

import static java.util.stream.Collectors.*;

@Service
@Transactional
public class CleaningServiceService {

    @Autowired
    private CleaningServiceRepository cleaningServiceRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CleaningDescriptionRepository cleaningDescriptionRepository;

    @Autowired
    private CleaningPriceRepository cleaningPriceRepository;

    public List<CleaningServiceMinimalView> findClientsCleaningServices(Long userId){

        return cleaningServiceRepository.findClientsCleaningServices(userId);
    }

    public CleaningService getCleaningService(Long id){

        return cleaningServiceRepository.findById(id)
                .orElseThrow(() -> new CleaningServiceNotFoundException(id));
    }

    public List<Appointment> findCleaningServiceAppointments(Long cleaningServiceId) {

        return appointmentRepository.findAllByCleaningService(cleaningServiceId);
    }

    public void createCleaningService(Long employeeId,
                                      Long clientId,
                                      CleaningServiceCommand cleaningServiceCommand,
                                      List<AppointmentCommand> appointmentCommands){

        Employee employee = (Employee) userRepository.findById(employeeId).orElseThrow(() -> new UserNotFoundException(employeeId));
        //TODO: this clientId can be null.
        Client client = (Client) userRepository.findById(clientId).orElseThrow(() -> new UserNotFoundException(clientId));

        CleaningService cleaningService = cleaningServiceCommand.toDomain(client);
        CleaningService savedCleaningService = cleaningServiceRepository.save(cleaningService);

        List<Appointment> appointments = appointmentCommands.stream()
                .map(command -> command.toDomain(savedCleaningService, employee))
                .collect(toList());

        appointmentRepository.saveAll(appointments);
    }


    public void addMessageToCleaningService(Long id, Message message){
        CleaningService cleaningService = cleaningServiceRepository.findById(id)
                .orElseThrow(() -> new CleaningServiceNotFoundException(id));

        CleaningService newCleaningService = new CleaningServiceBuilder()
                .withCleaningService(cleaningService)
                .withMessage(message)
                .build();

        cleaningServiceRepository.save(newCleaningService);
    }

    public Optional<CleaningDescription> getDescriptions(){

        return cleaningDescriptionRepository.findTopByOrderByIdDesc();
    }

    public Optional<CleaningPrice> getCleaningServicePrices(){

        return cleaningPriceRepository.findTopByOrderByIdDesc();
    }


//    public List<CleaningServiceDisplay> getCleaningServices(){
//        return null;
//    }
}
