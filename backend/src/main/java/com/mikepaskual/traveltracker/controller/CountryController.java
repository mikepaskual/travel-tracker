package com.mikepaskual.traveltracker.controller;

import com.mikepaskual.traveltracker.dto.CountryDto;
import com.mikepaskual.traveltracker.exception.CountryNotFoundException;
import com.mikepaskual.traveltracker.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<CountryDto> getCountries() {
        return countryService.getCountries();
    }

    @GetMapping("/{code}")
    public CountryDto getCountryByCode(@PathVariable String code) {
        return countryService.getCountryByCode(code)
                .orElseThrow(() -> new CountryNotFoundException(code));
    }
}
