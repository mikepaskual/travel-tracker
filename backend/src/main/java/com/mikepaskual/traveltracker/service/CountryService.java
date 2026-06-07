package com.mikepaskual.traveltracker.service;

import com.mikepaskual.traveltracker.client.RestCountriesClient;
import com.mikepaskual.traveltracker.dto.CountryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final RestCountriesClient restCountriesClient;

    public CountryService(RestCountriesClient restCountriesClient) {
        this.restCountriesClient = restCountriesClient;
    }

    public List<CountryDto> getCountries() {
        return restCountriesClient.getCountries();
    }

    public Optional<CountryDto> getCountryByCode(String code) {
        return restCountriesClient.getCountries()
                .stream()
                .filter(country -> country.code().equalsIgnoreCase(code))
                .findFirst();
    }
}
