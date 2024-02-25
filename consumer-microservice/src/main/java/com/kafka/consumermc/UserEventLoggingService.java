package com.kafka.consumermc;

import com.kafka.consumermc.model.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserEventLoggingService {
    @KafkaListener(topics = "user-created-event-topic", groupId = "unique-group-id")
    void saveUserEvent(UserCreatedEvent userCreatedEvent) {
        //todo сохранить лог в БД
        log.info("user created info saved, {}", userCreatedEvent);
    }

    
}
