package com.cleaning.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OfficeCleaningQuoteRequestDto {
    private final Long id;
    private final Long price;
    private final String description;
}
