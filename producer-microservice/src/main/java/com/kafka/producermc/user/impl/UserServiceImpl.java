package com.kafka.producermc.user.impl;

import com.kafka.producermc.dto.UserDto;
import com.kafka.producermc.user.User;
import com.kafka.producermc.user.UserMapper;
import com.kafka.producermc.user.UserRepository;
import com.kafka.producermc.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        final User user = userRepository.save(userMapper.toUser(userDto));
        return userMapper.toUserDto(user);
    }
}
