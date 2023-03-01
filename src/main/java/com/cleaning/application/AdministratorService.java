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
import java.util.stream.*;

import static java.util.stream.Collectors.groupingBy;

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

    @Autowired
    private CleaningServiceRepository cleaningServiceRepository;

    public void createEmployeeContract(User user){

        userRepository.save(user);
    }

    public List<User> getAllEmployees(){

        return userRepository.findAllByRole(Role.EMPLOYEE);
    }

    public Map<UserMinimalView, List<Appointment>> getAllEmployeesAppointmentsByDate(String date){

        List<UserMinimalView> employeesMinimalView = userRepository.findAllEmployeeMinimalViews();
        List<Appointment> appointments = appointmentRepository.findAllByCleaningDate(date);

        Map<Long, List<Appointment>> map = appointments.stream()
                .collect(groupingBy(ap -> ap.getEmployee().getId()));

        Map<UserMinimalView, List<Appointment>> userMinimalViewListMap = new LinkedHashMap<>();

        for(UserMinimalView view: employeesMinimalView) {
            List<Appointment> appointmentList = map.get(view.getId());
            userMinimalViewListMap.put(view, Objects.requireNonNullElse(appointmentList, Collections.emptyList()));
        }

        return userMinimalViewListMap;
    }

    public void createDescriptions(CleaningDescription cleaningDescription){

        cleaningDescriptionRepository.save(cleaningDescription);
    }

    public void createCleaningPrices(CleaningPrice cleaningPrice){

        cleaningPriceRepository.save(cleaningPrice);
    }


    public List<CleaningServiceMinimalView> getAllCleaningServices(){

        return cleaningServiceRepository.findAll();
    }
}
