package com.kafka.producermc.user;

import com.kafka.producermc.dto.UserDto;
import com.kafka.producermc.event.UserCreatedEvent;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserDto userDto);

    UserDto toUserDto(User user);

    UserCreatedEvent toUserCreatedEvent(User user);
}
