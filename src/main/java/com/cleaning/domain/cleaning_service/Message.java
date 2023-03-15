package com.cleaning.domain.cleaning_service;

import com.cleaning.domain.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.*;
import java.util.*;

@Embeddable
@NoArgsConstructor
@Getter
public class Message extends BaseEntity {

    @NotNull
    private LocalDateTime messageDate;

    @NotBlank
    private String sender;

    @Lob
    @NotBlank
    private String content;

    public Message(LocalDateTime messageDate, String sender, String content) {
        this.messageDate = messageDate;
        this.sender = sender;
        this.content = content;
        validate(this);
    }

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
