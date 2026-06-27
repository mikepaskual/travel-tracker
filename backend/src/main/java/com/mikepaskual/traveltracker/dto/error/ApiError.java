package com.mikepaskual.traveltracker.dto.error;

import java.time.LocalDateTime;

public record ApiError(
        LocalDateTime timestamp,
        Integer status,
        String message,
        String path
) {
}
