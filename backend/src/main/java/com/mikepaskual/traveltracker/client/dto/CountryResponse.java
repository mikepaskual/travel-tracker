package com.mikepaskual.traveltracker.client.dto;

import java.util.List;

public record CountryResponse(
        NameResponse names,
        CodeResponse codes,
        List<CapitalResponse> capitals,
        FlagResponse flag,
        String region
) {
}
