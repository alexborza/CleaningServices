package com.cleaning.facade;

import com.cleaning.entity.*;
import com.cleaning.facade.dto.OfficeCleaningDto;
import com.cleaning.facade.dto.OfficeCleaningQuoteRequestDto;
import com.cleaning.facade.mapper.OfficeCleaningServiceMapper;
import com.cleaning.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfficeCleaningServiceFacade {

    @Autowired
    private OfficeCleaningRepository repository;

    @Autowired
    private OfficeCleaningServiceMapper mapper;

    @Autowired
    private UserRepository userRepository;

    public void quoteRequestForOfficeCleaning(Long userId, OfficeCleaningDto officeCleaningDto){
        if(userId == null) {
            repository.save(mapper.toOfficeCleaningEntity(officeCleaningDto));
        } else {
            Client client = userRepository.findClientById(userId)
                    .orElseThrow(EntityNotFoundException::new);
            OfficeCleaning officeCleaning = mapper.toOfficeCleaningEntity(officeCleaningDto);
            client.addOfficeCleaning(officeCleaning);
            officeCleaning.setClient(client);
            userRepository.save(client);
        }
    }

    public void updateQuoteRequestForOfficeCleaning(Long id, OfficeCleaningQuoteRequestDto dto){
        OfficeCleaning officeCleaning = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        OfficeCleaningQuoteRequest quoteRequest = mapper.toOfficeCleaningQuoteRequestEntity(dto);
        officeCleaning.setQuoteRequest(quoteRequest);
        officeCleaning.setStatus(OfficeCleaningStatus.Sent);
        repository.save(officeCleaning);
    }

    public List<OfficeCleaningDto> getQuoteRequests(){
        List<OfficeCleaning> quoteRequests = (List<OfficeCleaning>)repository.findAll();
        return quoteRequests.stream()
                .map(mapper::toOfficeCleaningDto)
                .collect(Collectors.toList());
    }

    public OfficeCleaningDto getQuoteRequest(Long id){
        OfficeCleaning officeCleaning = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.toOfficeCleaningDto(officeCleaning);
    }
}
