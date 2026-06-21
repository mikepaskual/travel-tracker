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

    public List<CountryDto> getCountries() {
        RestCountriesResponse response = restClient.get()
                .uri(properties.uri() + "?responseFields=" + properties.responseFields())
                .header("Authorization", "Bearer " + properties.apiKey())
                .retrieve()
                .body(RestCountriesResponse.class);
        if (response == null) {
            return List.of();
        }
        return response.data().objects().stream()
                .map(this::toCountryDto)
                .toList();
    }

    private CountryDto toCountryDto(CountryResponse country) {
        return new CountryDto(
                country.codes().alpha_2(),
                country.names().common(),
                country.capitals() != null && !country.capitals().isEmpty()
                        ? country.capitals().get(0).name()
                        : "",
                country.region(),
                country.flag().url_svg(),
                country.flag().description()
        );
    }
}
