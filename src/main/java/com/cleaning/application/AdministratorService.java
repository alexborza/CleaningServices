package com.cleaning.application;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.cleaning_service.description.*;
import com.cleaning.domain.cleaning_service.prices.*;
import com.cleaning.domain.users.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional
public class AdministratorService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CleaningDescriptionRepository cleaningDescriptionRepository;

    @Autowired
    private CleaningPriceRepository cleaningPriceRepository;

    public void createEmployeeContract(User user){

        userRepository.save(user);
    }

    public List<User> getAllEmployees(){

        return userRepository.findAllByRole(Role.EMPLOYEE);
    }

    public List<Appointment> getAppointmentsByDate(String date){

        return appointmentRepository.findAllByCleaningDate(date);
    }

    public void createDescriptions(CleaningDescription cleaningDescription){

        cleaningDescriptionRepository.save(cleaningDescription);
    }

    public void createCleaningPrices(CleaningPrice cleaningPrice){

        cleaningPriceRepository.save(cleaningPrice);
    }
}
