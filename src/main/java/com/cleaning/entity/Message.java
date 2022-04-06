package com.cleaning.entity;

import lombok.*;

import javax.persistence.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Message {
    private String messageDate;
    private String sender;

    @Lob
    private String content;
}
