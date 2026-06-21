package com.mikepaskual.traveltracker.client.dto;

public record FlagResponse(
        String description,
        String url_png,
        String url_svg
) {
}
