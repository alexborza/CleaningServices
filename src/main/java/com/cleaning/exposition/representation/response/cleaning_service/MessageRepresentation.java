package com.cleaning.exposition.representation.response.cleaning_service;

import lombok.*;

@Getter
@AllArgsConstructor
public class MessageRepresentation {
    private final String messageDate;
    private final String sender;
    private final String content;
}
