package com.mikepaskual.traveltracker.controller;

import com.mikepaskual.traveltracker.service.FavoriteCountryService;
import org.springframework.web.bind.annotation.*;

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

}
