package com.mikepaskual.traveltracker.dto;

public record CountryDto(
        String code,
        String name,
        String capital,
        String region,
        String flagUrl
) {
}
