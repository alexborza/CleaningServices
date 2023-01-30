package com.cleaning.application;

import com.cleaning.domain.cleaning_service.*;
import com.cleaning.domain.cleaning_service.description.*;
import com.cleaning.domain.users.*;
import com.cleaning.infrastructure.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.*;

@Service
public class CleaningServiceService {

//    @Autowired
//    private CleaningServiceRepository repo;
//
//    @Autowired
//    private ClientRepository clientRepository;
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @Autowired
//    private UserRepository<User> userRepository;
//
//    @Autowired
//    private CleaningServiceDescriptionRepository cleaningServiceDescriptionRepository;
//
//    @Autowired
//    private CleaningServicePricesRepository cleaningServicePricesRepository;
//
//    public void createCleaningService(Long employeeId, Long userId, CleaningServiceDto cleaningServiceDto){
//    }
//
//    public List<CleaningServiceDisplay> getCleaningServices(){
//        return null;
//    }
//
//    public CleaningServiceDto getCleaningService(Long id){
//        CleaningService cleaningService = repo.findById(id).orElseThrow(EntityNotFoundException::new);
//        return mapper.toCleaningServiceDto(cleaningService);
//    }
//
//    public void endCleaningService(Long id){
//
//    }
//
//    public void finishCleaningService(Long id, String date) {
//
//    }
//
//    public List<CleaningDateDto> getDatesOfCleaningForCleaningService(Long id){
//        return null;
//    }
//
//    public void addMessageToCleaningService(Long id, MessageDto dto){
//    }
//
//    public List<MessageDto> getMessagesForCleaningService(Long id){
//        return repo.getMessagesForCleaningService(id).stream()
//                .filter(Objects::nonNull)
//                .map(mapper::toMessageDto)
//                .collect(Collectors.toList());
//    }
//
//    public CleaningDateDto getNextCleaningDate(Long id){
//        return null;
//    }
//
//    public CleaningServiceDescriptionDto getDescriptions(){
//        CleaningDescription entity = cleaningServiceDescriptionRepository.findFirstBy().orElse(new CleaningDescription());
//        return cleaningServiceDescriptionMapper.toCleaningServiceDescriptionDto(entity);
//    }
//
//    public CleaningServicePricesDto getCleaningServicePrices(){
//        return null;
//    }
//
//    public List<DatesToRescheduleDto> getDatesToReschedule(Long id){
//        return null;
//    }
//
//    public void rescheduleCleaningService(Long id, RescheduleDateDto dto){
//
//    }
}
