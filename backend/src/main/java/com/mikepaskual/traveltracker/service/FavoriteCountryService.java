package com.mikepaskual.traveltracker.service;

import com.mikepaskual.traveltracker.builder.GenericBuilder;
import com.mikepaskual.traveltracker.dto.FavoriteCountryDto;
import com.mikepaskual.traveltracker.entity.FavoriteCountry;
import com.mikepaskual.traveltracker.repository.FavoriteCountryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FavoriteCountryService {

    private final FavoriteCountryRepository favoriteCountryRepository;

    public FavoriteCountryService(FavoriteCountryRepository favoriteCountryRepository) {
        this.favoriteCountryRepository = favoriteCountryRepository;
    }

    public Set<String> getFavoriteCountryCodes() {
        return favoriteCountryRepository.findAll()
                .stream()
                .map(FavoriteCountry::getCountryCode)
                .collect(Collectors.toSet());
    }

    public void addFavorite(String countryCode) {
        String countryCodeUpper = countryCode.toUpperCase();

        if (!favoriteCountryRepository.existsById(countryCodeUpper)) {
            favoriteCountryRepository.save(GenericBuilder.of(FavoriteCountry::new)
                    .with(FavoriteCountry::setCountryCode, countryCodeUpper)
                    .with(FavoriteCountry::setCreatedAt, LocalDateTime.now())
                    .build());
        }
    }

    public void removeFavorite(String countryCode) {
        String countryCodeUpper = countryCode.toUpperCase();

        if (favoriteCountryRepository.existsById(countryCodeUpper)) {
            favoriteCountryRepository.deleteById(countryCodeUpper);
        }
    }

    public List<FavoriteCountryDto> getFavoriteCountries() {
        return favoriteCountryRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    private FavoriteCountryDto toDto(FavoriteCountry favoriteCountry) {
        return new FavoriteCountryDto(
                favoriteCountry.getCountryCode(),
                favoriteCountry.getCreatedAt());
    }
}
