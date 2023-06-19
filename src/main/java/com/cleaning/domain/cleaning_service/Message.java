package com.cleaning.domain.cleaning_service;

import com.cleaning.domain.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.*;

@Embeddable
@NoArgsConstructor
@Getter
public class Message extends BaseEntity {

    @NotNull
    private LocalDateTime messageDate;

    @NotBlank
    private String sender;

    @NotBlank
    private String content;

    public Message(LocalDateTime messageDate, String sender, String content) {
        this.messageDate = messageDate;
        this.sender = sender;
        this.content = content;
        validate(this);
    }
}
