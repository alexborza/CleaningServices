package com.cleaning.exposition.representation.response.cleaning_service;

import com.cleaning.domain.cleaning_service.*;
import lombok.*;

@Getter
@AllArgsConstructor
public class MessageRepresentation {
    private final String messageDate;
    private final String sender;
    private final String content;

    public static MessageRepresentation fromDomain(Message message) {

        return new MessageRepresentation(
                message.getMessageDate(),
                message.getSender(),
                message.getContent()
        );
    }

    public Message toDomain() {

        return new Message(
                messageDate,
                sender,
                content
        );
    }
}
