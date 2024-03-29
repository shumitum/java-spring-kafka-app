package com.kafka.producermc.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kafka.producermc.util.Utils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserCreatedEvent {
    private Long id;
    private String name;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Utils.DATE_TIME_PATTERN)
    private LocalDateTime createdAt;
}
