package com.example.springLPP;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.LocalDateTime;

public record Task(
        @Null
        Long id,
        @NotNull
        Long creatorId,
        @NotNull
        Long makerId,
        @Null
        Status status,
        @FutureOrPresent
        @NotNull
        LocalDateTime createDataTime,
        @FutureOrPresent
        @NotNull
        LocalDateTime deadlineData,
        @NotNull
        Priority priority) {
}
