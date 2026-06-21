package com.mikepaskual.traveltracker.controller;

import com.mikepaskual.traveltracker.dto.FavoriteCountryDto;
import com.mikepaskual.traveltracker.service.FavoriteCountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteCountryController {

    private final FavoriteCountryService favoriteCountryService;

    public FavoriteCountryController(FavoriteCountryService favoriteCountryService) {
        this.favoriteCountryService = favoriteCountryService;
    }

    @PostMapping("/{countryCode}")
    public void addFavorite(@PathVariable String countryCode) {
        favoriteCountryService.addFavorite(countryCode);
    }

    @GetMapping
    public List<FavoriteCountryDto> getFavorites() {
        return favoriteCountryService.getFavoriteCountries();
    }

}
