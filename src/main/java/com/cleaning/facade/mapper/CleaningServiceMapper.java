package com.cleaning.facade.mapper;

import com.cleaning.entity.*;
import com.cleaning.facade.dto.*;
import com.cleaning.facade.vo.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CleaningServiceMapper {
    default CleaningDetails toCleaningDetailsEntity(CleaningDetailsDto cleaningDetailsDto) {
        if (cleaningDetailsDto instanceof StandardCleaningDetailsDto) {
            return toStandardCleaningDetailsEntity((StandardCleaningDetailsDto) cleaningDetailsDto);
        }

        if (cleaningDetailsDto instanceof PostConstructionCleaningDetailsDto) {
            return toPostConstructionCleaningDetailsEntity((PostConstructionCleaningDetailsDto) cleaningDetailsDto);
        }

        if (cleaningDetailsDto instanceof DisinfectionCleaningDetailsDto) {
            return toDisinfectionCleaningDetailsEntity((DisinfectionCleaningDetailsDto) cleaningDetailsDto);
        }

        throw new IllegalArgumentException("Unknown subtype of CleaningDetailsDto");
    }

    CleaningService toCleaningServiceEntity(CleaningServiceDto cleaningServiceDto);
    ContactInfo toContactInfoEntity(ContactInfoDto contactInfoDto);
    Location toLocationEntity(LocationDto locationDto);
    CleaningDate toCleaningDateEntity(CleaningDateDto cleaningDateDto);
    Message toCleaningMessageEntity(MessageDto dto);
    RescheduleDate toRescheduleDateEntity(RescheduleDateDto dto);
    StandardCleaningDetails toStandardCleaningDetailsEntity(StandardCleaningDetailsDto standardCleaningDetailsDto);
    PostConstructionCleaningDetails toPostConstructionCleaningDetailsEntity(PostConstructionCleaningDetailsDto postConstructionCleaningDetailsDto);
    DisinfectionCleaningDetails toDisinfectionCleaningDetailsEntity(DisinfectionCleaningDetailsDto disinfectionCleaningDetailsDto);

    default CleaningDetailsDto toCleaningDetailsDto(CleaningDetails cleaningDetails) {
        if (cleaningDetails instanceof StandardCleaningDetails) {
            return toStandardCleaningDetailsDto((StandardCleaningDetails) cleaningDetails);
        }

        if (cleaningDetails instanceof PostConstructionCleaningDetails) {
            return toPostConstructionCleaningDetailsDto((PostConstructionCleaningDetails) cleaningDetails);
        }

        if (cleaningDetails instanceof DisinfectionCleaningDetails) {
            return toDisinfectionCleaningDetailsDto((DisinfectionCleaningDetails) cleaningDetails);
        }

        throw new IllegalArgumentException("Unknown subtype of CleaningDetails");
    }

    CleaningServiceDto toCleaningServiceDto(CleaningService cleaningService);
    MessageDto toMessageDto(Message message);

    StandardCleaningDetailsDto toStandardCleaningDetailsDto(StandardCleaningDetails standardCleaningDetails);
    PostConstructionCleaningDetailsDto toPostConstructionCleaningDetailsDto(PostConstructionCleaningDetails postConstructionCleaningDetails);
    DisinfectionCleaningDetailsDto toDisinfectionCleaningDetailsDto(DisinfectionCleaningDetails disinfectionCleaningDetails);
    CleaningDateDto toCleaningDateDto(CleaningDate cleaningDate);

    CleaningServiceDisplay toCleaningServiceDisplay(CleaningServiceDisplayVO vo);
}
