package com.kafka.consumermc.handler;

import com.kafka.consumermc.model.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventLoggingHandler {
    @KafkaListener(topics = "user-created-event-topic", groupId = "unique-group-id")
    void saveUserEvent(UserCreatedEvent userCreatedEvent) {
        //todo сохранить лог в БД
        log.info("user-created-event was saved, {}", userCreatedEvent);
    }

    
}
