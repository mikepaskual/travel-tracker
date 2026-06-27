package com.mikepaskual.traveltracker.service;

import com.mikepaskual.traveltracker.client.RestCountriesClient;
import com.mikepaskual.traveltracker.dto.CountryDto;
import com.mikepaskual.traveltracker.testdata.CountryResponseMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {

    @Mock
    private FavoriteCountryService favoriteCountryService;
    @Mock
    private RestCountriesClient restCountriesClient;

    @InjectMocks
    private CountryService service;

    @Test
    void should_mark_favorite_countries() {
        // given
        when(restCountriesClient.getCountries())
                .thenReturn(List.of(
                        CountryResponseMother.spain(),
                        CountryResponseMother.france()
                ));
        when(favoriteCountryService.getFavoriteCountryCodes())
                .thenReturn(Set.of("ES"));
        // when
        List<CountryDto> result = service.getCountries();
        // then
        CountryDto spain = result.stream()
                .filter(country -> country.code().equals("ES"))
                .findFirst()
                .orElseThrow();
        CountryDto france = result.stream()
                .filter(country -> country.code().equals("FR"))
                .findFirst()
                .orElseThrow();
        assertAll(
                () -> assertTrue(spain.favorite()),
                () -> assertFalse(france.favorite())
        );
    }

    @Test
    void should_return_empty_list_when_no_countries_exist() {
        // given
        when(restCountriesClient.getCountries())
                .thenReturn(List.of());
        // when
        List<CountryDto> result = service.getCountries();
        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void should_return_country_marked_as_favorite() {
        // given
        when(restCountriesClient.getCountryByCode("ES"))
                .thenReturn(Optional.of(CountryResponseMother.spain()));
        when(favoriteCountryService.getFavoriteCountryCodes())
                .thenReturn(Set.of("ES"));
        // when
        Optional<CountryDto> result = service.getCountryByCode("ES");
        // then
        assertTrue(result.isPresent());

        CountryDto country = result.get();
        assertAll(
                () -> assertEquals("ES", country.code()),
                () -> assertTrue(country.favorite())
        );
    }

    @Test
    void should_not_return_country_when_does_not_exist() {
        // given
        when(restCountriesClient.getCountryByCode("XX"))
                .thenReturn(Optional.empty());
        // when
        Optional<CountryDto> result = service.getCountryByCode("XX");
        // then
        assertFalse(result.isPresent());
    }
}
