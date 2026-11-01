package com.example.springLPP;

import java.time.LocalDateTime;

public record ErrorDTO(
        String userMessage,
        String detailMessage,
        LocalDateTime errorTime
) {
}
