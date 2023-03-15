package com.cleaning.domain.cleaning_service;

import com.cleaning.domain.*;
import com.cleaning.infrastructure.cleaning_service.data.*;
import org.junit.jupiter.api.*;

import java.time.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MessageTest {

    @Test
    void should_create() {
        Message message = MessageTestData.dummyMessage("message");

        assertThat(message.getMessageDate()).isNotNull();
        assertThat(message.getContent()).isEqualTo("message");
        assertThat(message.getSender()).isEqualTo("sender");
    }

    @Test
    void should_not_create_when_message_date_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> MessageTestData.dummyMessage(null, "s", "c"));
    }

    @Test
    void should_not_create_when_sender_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> MessageTestData.dummyMessage(LocalDateTime.now(), null, "c"));
    }

    @Test
    void should_not_create_when_sender_blank() {
        assertThrows(DomainConstraintViolationException.class,
                () -> MessageTestData.dummyMessage(LocalDateTime.now(), "", "c"));
    }

    @Test
    void should_not_create_when_content_null() {
        assertThrows(DomainConstraintViolationException.class,
                () -> MessageTestData.dummyMessage(LocalDateTime.now(), "Sender", null));
    }

    @Test
    void should_not_create_when_content_blank() {
        assertThrows(DomainConstraintViolationException.class,
                () -> MessageTestData.dummyMessage(LocalDateTime.now(), "sender", ""));
    }
}
