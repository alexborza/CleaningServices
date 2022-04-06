package com.cleaning.facade.dto;

import lombok.*;

@Getter
@AllArgsConstructor
public class MessageDto {
    private final String messageDate;
    private final String sender;
    private final String content;
}
