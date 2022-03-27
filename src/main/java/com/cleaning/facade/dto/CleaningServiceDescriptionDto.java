package com.cleaning.facade.dto;

import lombok.*;


@Getter
@AllArgsConstructor
public class CleaningServiceDescriptionDto {
    private final Long id;
    private final String standardCleaningDescription;
    private final String deepCleaningDescription;
    private final String postConstructionCleaningDescription;
    private final String disinfectionCleaningDescription;
}
