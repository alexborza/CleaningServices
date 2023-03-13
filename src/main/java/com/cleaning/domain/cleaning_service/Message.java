package com.cleaning.domain.cleaning_service;

import lombok.*;

import javax.persistence.*;
import java.time.*;
import java.util.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Message {
    private LocalDateTime messageDate;
    private String sender;

    @Lob
    private String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(messageDate, message.messageDate) && Objects.equals(sender, message.sender) && Objects.equals(content, message.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageDate, sender, content);
    }
}
