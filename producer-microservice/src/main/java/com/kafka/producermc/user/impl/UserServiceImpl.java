package com.kafka.producermc.user.impl;

import com.kafka.producermc.config.KafkaConfig;
import com.kafka.producermc.dto.UserDto;
import com.kafka.producermc.event.UserCreatedEvent;
import com.kafka.producermc.user.User;
import com.kafka.producermc.user.UserMapper;
import com.kafka.producermc.user.UserRepository;
import com.kafka.producermc.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        final User user = userRepository.save(userMapper.toUser(userDto));
        final UserCreatedEvent userCreatedEvent = userMapper.toUserCreatedEvent(user);
        userCreatedEvent.setCreatedAt(LocalDateTime.now());

        kafkaTemplate.send(KafkaConfig.USER_CREATED_TOPIC, userCreatedEvent);

        return userMapper.toUserDto(user);
    }
}
