package com.cleaning.facade;

import com.cleaning.entity.OfficeCleaning;
import com.cleaning.entity.OfficeCleaningQuoteRequest;
import com.cleaning.entity.OfficeCleaningStatus;
import com.cleaning.facade.dto.OfficeCleaningDto;
import com.cleaning.facade.dto.OfficeCleaningQuoteRequestDto;
import com.cleaning.facade.mapper.OfficeCleaningServiceMapper;
import com.cleaning.repository.OfficeCleaningRepository;
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

    public void quoteRequestForOfficeCleaning(OfficeCleaningDto officeCleaningDto){
        repository.save(mapper.toOfficeCleaningEntity(officeCleaningDto));
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
