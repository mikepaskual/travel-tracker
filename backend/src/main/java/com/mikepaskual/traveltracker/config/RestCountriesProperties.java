package com.mikepaskual.traveltracker.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "restcountries")
public record RestCountriesProperties(
        String baseUrl,
        String countriesPath,
        String apiKey,
        String responseFields
) {
}
