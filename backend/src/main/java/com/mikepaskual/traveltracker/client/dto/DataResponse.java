package com.mikepaskual.traveltracker.client.dto;

import java.util.List;

public record DataResponse(
        List<CountryResponse> objects
) {
}
