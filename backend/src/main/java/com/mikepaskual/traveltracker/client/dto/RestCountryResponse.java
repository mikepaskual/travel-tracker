package com.mikepaskual.traveltracker.client.dto;

import java.util.List;

public record RestCountryResponse(
        NameResponse name,
        String cca2,
        List<String> capital,
        String region,
        FlagsResponse flags
) {
}
