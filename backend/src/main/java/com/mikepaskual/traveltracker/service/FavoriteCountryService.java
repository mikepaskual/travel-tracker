package com.mikepaskual.traveltracker.service;

import com.mikepaskual.traveltracker.builder.GenericBuilder;
import com.mikepaskual.traveltracker.entity.FavoriteCountry;
import com.mikepaskual.traveltracker.repository.FavoriteCountryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FavoriteCountryService {

    private final FavoriteCountryRepository favoriteCountryRepository;

    public FavoriteCountryService(FavoriteCountryRepository favoriteCountryRepository) {
        this.favoriteCountryRepository = favoriteCountryRepository;
    }

    public void addFavorite(String countryCode) {
        String countryCodeUppered = countryCode.toUpperCase();

        if (!favoriteCountryRepository.existsById(countryCodeUppered)) {
            favoriteCountryRepository.save(GenericBuilder.of(FavoriteCountry::new)
                    .with(FavoriteCountry::setCountryCode, countryCodeUppered)
                    .with(FavoriteCountry::setCreatedAt, LocalDateTime.now())
                    .build());
        }
    }
}
