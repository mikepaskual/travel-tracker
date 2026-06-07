package com.mikepaskual.traveltracker.service;

import com.mikepaskual.traveltracker.client.RestCountriesClient;
import com.mikepaskual.traveltracker.dto.CountryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final RestCountriesClient restCountriesClient;

    public CountryService(RestCountriesClient restCountriesClient) {
        this.restCountriesClient = restCountriesClient;
    }

    public List<CountryDto> getCountries() {
        return restCountriesClient.getCountries();
    }
}
