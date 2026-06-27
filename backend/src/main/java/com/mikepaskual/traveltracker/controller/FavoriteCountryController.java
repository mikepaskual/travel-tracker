package com.mikepaskual.traveltracker.controller;

import com.mikepaskual.traveltracker.dto.FavoriteCountryDto;
import com.mikepaskual.traveltracker.exception.CountryNotFoundException;
import com.mikepaskual.traveltracker.service.CountryService;
import com.mikepaskual.traveltracker.service.FavoriteCountryService;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/favorites")
public class FavoriteCountryController {

    private final FavoriteCountryService favoriteCountryService;
    private final CountryService countryService;

    public FavoriteCountryController(
            FavoriteCountryService favoriteCountryService,
            CountryService countryService) {
        this.favoriteCountryService = favoriteCountryService;
        this.countryService = countryService;
    }

    @PostMapping("/{countryCode}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addFavorite(
            @PathVariable
            @Pattern(
                    regexp = "^[A-Za-z]{2}$",
                    message = "Country code must consist of two letters")
            String countryCode) {
        countryService.getCountryByCode(countryCode)
                        .orElseThrow(() -> new CountryNotFoundException(countryCode));

        favoriteCountryService.addFavorite(countryCode);
    }

    @DeleteMapping("/{countryCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFavorite(@PathVariable String countryCode) {
        favoriteCountryService.removeFavorite(countryCode);
    }

    @GetMapping
    public List<FavoriteCountryDto> getFavorites() {
        return favoriteCountryService.getFavoriteCountries();
    }

}
