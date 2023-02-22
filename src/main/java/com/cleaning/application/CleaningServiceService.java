package com.cleaning.application;

import com.cleaning.domain.appointment.*;
import com.cleaning.domain.cleaning_service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class CleaningServiceService {

    @Autowired
    private CleaningServiceRepository cleaningServiceRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

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

//    public void createCleaningService(Long employeeId, Long userId, CleaningServiceDto cleaningServiceDto){
//    }
//
//    public List<CleaningServiceDisplay> getCleaningServices(){
//        return null;
//    }
//

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
