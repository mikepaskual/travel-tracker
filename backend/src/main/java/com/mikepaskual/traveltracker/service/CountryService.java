package com.mikepaskual.traveltracker.service;

import com.mikepaskual.traveltracker.client.RestCountriesClient;
import com.mikepaskual.traveltracker.client.dto.CountryResponse;
import com.mikepaskual.traveltracker.dto.CountryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CountryService {

    private final RestCountriesClient restCountriesClient;
    private final FavoriteCountryService favoriteCountryService;

    public CountryService(RestCountriesClient restCountriesClient, FavoriteCountryService favoriteCountryService) {
        this.restCountriesClient = restCountriesClient;
        this.favoriteCountryService = favoriteCountryService;
    }

    public List<CountryDto> getCountries() {
        return restCountriesClient.getCountries()
                .stream()
                .map(country -> toCountryDto(country, getFavoriteCodes()))
                .toList();
    }

    public Optional<CountryDto> getCountryByCode(String countryCode) {
        return restCountriesClient.getCountryByCode(countryCode)
                .map(country -> toCountryDto(country, getFavoriteCodes()));
    }

    private Set<String> getFavoriteCodes() {
        return favoriteCountryService.getFavoriteCountryCodes();
    }

    private CountryDto toCountryDto(CountryResponse country,
                                    Set<String> favoriteCodes) {
        return new CountryDto(
                country.codes().alpha_2(),
                country.names().common(),
                country.capitals() != null && !country.capitals().isEmpty()
                        ? country.capitals().get(0).name()
                        : "",
                country.region(),
                country.flag().url_svg(),
                country.flag().description(),
                favoriteCodes.contains(country.codes().alpha_2())
        );
    }
}
