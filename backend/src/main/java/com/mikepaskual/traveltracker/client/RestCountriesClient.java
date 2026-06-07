package com.mikepaskual.traveltracker.client;

import com.mikepaskual.traveltracker.client.dto.RestCountryResponse;
import com.mikepaskual.traveltracker.config.RestCountriesProperties;
import com.mikepaskual.traveltracker.dto.CountryDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
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
        String url = properties.baseUrl()
                + properties.countriesPath()
                + "?fields="
                + properties.fields();
        RestCountryResponse[] response =
                restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(RestCountryResponse[].class);
        if (response == null) {
            return List.of();
        }
        return Arrays.stream(response)
                .map(this::toCountryDto)
                .toList();
    }

    private CountryDto toCountryDto(RestCountryResponse country) {
        return new CountryDto(
                country.cca2(),
                country.name().common(),
                country.capital() != null && !country.capital().isEmpty()
                        ? country.capital().get(0)
                        : "",
                country.region(),
                country.flags().svg()
        );
    }
}
