package com.cleaning.infrastructure.cleaning_service.data;

import com.cleaning.domain.cleaning_service.*;

import java.time.*;
import java.util.*;

public class MessageTestData {

    public static List<Message> dummyMessages() {
        return List.of(dummyMessage("message1"), dummyMessage("message2"));
    }

    public static Message dummyMessage(String message) {
        return new Message(LocalDateTime.now(), "sender", message);
    }

    public static Message dummyMessage(LocalDateTime localDateTime, String sender, String content) {
        return new Message(localDateTime, sender, content);
    }
}
