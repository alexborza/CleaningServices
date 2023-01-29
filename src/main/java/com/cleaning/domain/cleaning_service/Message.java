package com.cleaning.domain.cleaning_service;

import lombok.*;

import javax.persistence.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Message {
    private String messageDate;
    private String sender;

    @Lob
    private String content;
}
