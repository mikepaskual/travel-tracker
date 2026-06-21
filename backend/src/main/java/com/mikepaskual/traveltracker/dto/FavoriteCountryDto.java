package com.mikepaskual.traveltracker.dto;

import java.time.LocalDateTime;

public record FavoriteCountryDto(
        String countryCode,
        LocalDateTime createdAt
) {
}
