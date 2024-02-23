package com.kafka.producermc.user;

import com.kafka.producermc.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
}
