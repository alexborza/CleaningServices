package com.cleaning.application;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.cleaning_service.description.*;
import com.cleaning.domain.cleaning_service.prices.*;
import com.cleaning.domain.users.*;
import com.cleaning.exposition.representation.response.appointment.*;
import com.cleaning.exposition.representation.response.cleaning_service.description.*;
import com.cleaning.exposition.representation.response.cleaning_service.prices.*;
import com.cleaning.exposition.representation.response.users.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class AdministratorService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CleaningDescriptionRepository cleaningDescriptionRepository;

    @Autowired
    private CleaningPriceRepository cleaningPriceRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void createEmployeeContract(UserRepresentation representation){
        String encodedPassword = encoder.encode(representation.getPassword());
        representation.setPassword(encodedPassword);

        User user = representation.toDomain();
        userRepository.save(user);
    }

    public List<UserRepresentation> getAllEmployees(){
        List<User> employees = userRepository.findAllByRole(Role.EMPLOYEE);

        return employees.stream()
                .map(UserRepresentation::fromDomain)
                .collect(Collectors.toList());
    }

    public List<AppointmentRepresentation> getAppointmentsByDate(String date){

        List<Appointment> appointmentList = appointmentRepository.findAllByCleaningDate(date);

        return appointmentList.stream()
                .map(AppointmentRepresentation::fromDomain)
                .collect(Collectors.toList());

    }

    public void createDescriptions(CleaningDescriptionRepresentation representation){

        CleaningDescription cleaningDescription = representation.toDomain();
        cleaningDescriptionRepository.save(cleaningDescription);
    }

    public void createCleaningPrices(CleaningPricesRepresentation representation){

        CleaningPrice cleaningPrice = representation.toDomain();
        cleaningPriceRepository.save(cleaningPrice);
    }
}
