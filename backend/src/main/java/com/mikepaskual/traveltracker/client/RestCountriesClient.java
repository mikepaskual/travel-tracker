package com.mikepaskual.traveltracker.client;

import com.mikepaskual.traveltracker.dto.CountryDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestCountriesClient {

    public List<CountryDto> getCountries() {
        return List.of(
                new CountryDto("ES", "Spain", "Madrid", "Europe", "https://flagcdn.com/es.svg"),
                new CountryDto("FR", "France", "Paris", "Europe", "https://flagcdn.com/fr.svg")
        );
    }
}
