package com.cleaning.exposition.representation.request.cleaning_service;

import com.cleaning.domain.cleaning_service.*;
import lombok.*;

import java.time.*;

@AllArgsConstructor
@Getter
public class MessageCreation {
    private final String sender;
    private final String content;

    public Message toDomain() {

        return new Message(
                LocalDateTime.now(),
                sender,
                content
        );
    }
}
