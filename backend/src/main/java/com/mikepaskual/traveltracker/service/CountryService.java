package com.mikepaskual.traveltracker.service;

import com.mikepaskual.traveltracker.dto.CountryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    public List<CountryDto> getCountries() {
        return List.of(
                new CountryDto("ES", "Spain", "Madrid", "Europe", "https://flagcdn.com/es.svg"),
                new CountryDto("FR", "France", "Paris", "Europe", "https://flagcdn.com/fr.svg")
        );
    }
}
