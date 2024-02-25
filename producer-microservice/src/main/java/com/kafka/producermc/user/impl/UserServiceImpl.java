package com.kafka.producermc.user.impl;

import com.kafka.producermc.dto.UserDto;
import com.kafka.producermc.event.UserCreatedEvent;
import com.kafka.producermc.user.User;
import com.kafka.producermc.user.UserMapper;
import com.kafka.producermc.user.UserRepository;
import com.kafka.producermc.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

    @Value("${application.kafka.user-created-topic}")
    private String userCreatedEventTopic;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        final User user = userRepository.save(userMapper.toUser(userDto));
        final UserCreatedEvent userCreatedEvent = userMapper.toUserCreatedEvent(user);
        log.info("user was created, {}", user);
        userCreatedEvent.setCreatedAt(LocalDateTime.now());

        sendMessage(userCreatedEvent);

        return userMapper.toUserDto(user);
    }

    private void sendMessage(UserCreatedEvent userCreatedEvent) {
        CompletableFuture<SendResult<String, UserCreatedEvent>> future = kafkaTemplate
                .send(userCreatedEventTopic, userCreatedEvent);
        future.whenComplete((result, exception) -> {
            if (exception != null) {
                log.error("failed to send, {}", exception.getMessage());
            } else {
                log.info("event was sent successfully, {}", result.getRecordMetadata());
            }
        });
    }
}
