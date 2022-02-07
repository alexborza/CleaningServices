package com.cleaning.facade;

import com.cleaning.entity.CleaningService;
import com.cleaning.facade.dto.CleaningServiceDto;
import com.cleaning.facade.mapper.CleaningServiceMapper;
import com.cleaning.repository.CleaningServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CleaningServiceFacade {

    @Autowired
    private CleaningServiceRepository repo;

    @Autowired
    private CleaningServiceMapper mapper;

    public void createCleaningService(CleaningServiceDto cleaningServiceDto){
        repo.save(mapper.toCleaningServiceEntity(cleaningServiceDto));
    }

    public List<String> getBookedHoursForDate(String date){
        return repo.getBookedHoursForDate(date);
    }

    public List<CleaningServiceDto> getCleaningServices(){
        List<CleaningService> cleaningServices = (List<CleaningService>) repo.findAll();
        return cleaningServices.stream()
                .map(mapper::toCleaningServiceDto)
                .collect(Collectors.toList());
    }

    public CleaningServiceDto getCleaningService(Long id){
        CleaningService cleaningService = repo.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.toCleaningServiceDto(cleaningService);
    }
}
