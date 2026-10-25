package com.example.springLPP;

import java.time.LocalDateTime;

public record Task(
        Long id,
        Long creatorId,
        Long makerId,
        Status status,
        LocalDateTime createDataTime,
        LocalDateTime deadlineData,
        Priority priority) {
}
