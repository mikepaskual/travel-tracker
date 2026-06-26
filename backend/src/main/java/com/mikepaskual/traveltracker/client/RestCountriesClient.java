package com.mikepaskual.traveltracker.client;

import com.mikepaskual.traveltracker.client.dto.CountryResponse;
import com.mikepaskual.traveltracker.client.dto.RestCountriesResponse;
import com.mikepaskual.traveltracker.config.RestCountriesProperties;
import com.mikepaskual.traveltracker.dto.CountryDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class RestCountriesClient {

    private final RestClient restClient;
    private final RestCountriesProperties properties;

    public RestCountriesClient(RestClient restClient, RestCountriesProperties properties) {
        this.restClient = restClient;
        this.properties = properties;
    }

    public List<CountryResponse> getCountries() {
        RestCountriesResponse response = restClient.get()
                .uri(properties.uri() + "?responseFields=" + properties.responseFields())
                .header("Authorization", "Bearer " + properties.apiKey())
                .retrieve()
                .body(RestCountriesResponse.class);
        if (response == null) {
            return List.of();
        }
        return response.data().objects();
    }

}
