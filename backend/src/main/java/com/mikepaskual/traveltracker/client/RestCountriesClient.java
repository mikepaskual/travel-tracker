package com.mikepaskual.traveltracker.client;

import com.mikepaskual.traveltracker.client.dto.CountryResponse;
import com.mikepaskual.traveltracker.client.dto.RestCountriesResponse;
import com.mikepaskual.traveltracker.config.RestCountriesProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Component
public class RestCountriesClient {

    private final RestClient restClient;
    private final RestCountriesProperties properties;

    public RestCountriesClient(RestCountriesProperties properties) {
        this.properties = properties;
        this.restClient = RestClient.builder()
                .baseUrl(this.properties.baseUrl())
                .build();
    }

    public Optional<CountryResponse> getCountryByCode(String countryCode) {
        RestCountriesResponse response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(properties.countriesPath() + "/codes.alpha_2/{code}")
                        .queryParam("responseFields", properties.responseFields())
                        .build(countryCode.toUpperCase()))
                .header("Authorization", "Bearer " + properties.apiKey())
                .retrieve()
                .body(RestCountriesResponse.class);
        if (response == null || response.data().objects().isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(response.data().objects().get(0));
    }

    public List<CountryResponse> getCountries() {
        RestCountriesResponse response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(properties.countriesPath())
                        .queryParam("responseFields", properties.responseFields())
                        .build())
                .header("Authorization", "Bearer " + properties.apiKey())
                .retrieve()
                .body(RestCountriesResponse.class);
        if (response == null) {
            return List.of();
        }
        return response.data().objects();
    }

}
