package com.mikepaskual.traveltracker.service;

import com.mikepaskual.traveltracker.builder.GenericBuilder;
import com.mikepaskual.traveltracker.dto.FavoriteCountryDto;
import com.mikepaskual.traveltracker.entity.FavoriteCountry;
import com.mikepaskual.traveltracker.repository.FavoriteCountryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FavoriteCountryServiceTest {

    @Mock
    private FavoriteCountryRepository repo;

    @InjectMocks
    private FavoriteCountryService service;

    @Test
    void should_save_favorite_country_when_country_does_not_exists() {
        // given
        when(repo.existsById("ES")).thenReturn(false);
        // when
        service.addFavorite("ES");
        // then
        verify(repo).save(any(FavoriteCountry.class));
    }

    @Test
    void should_not_save_favorite_country_when_country_already_exists() {
        // given
        when(repo.existsById("ES")).thenReturn(true);
        // when
        service.addFavorite("ES");
        // then
        verify(repo, never()).save(any(FavoriteCountry.class));
    }

    @Test
    void should_delete_favorite_country_when_country_exists() {
        // given
        when(repo.existsById("ES")).thenReturn(true);
        // when
        service.removeFavorite("ES");
        // then
        verify(repo).deleteById("ES");
    }

    @Test
    void should_not_delete_favorite_country_when_country_does_not_exists() {
        // given
        when(repo.existsById("ES")).thenReturn(false);
        // when
        service.removeFavorite("ES");
        // then
        verify(repo, never()).deleteById("ES");
    }

    @Test
    void should_return_all_favorite_country_codes() {
        // given
        FavoriteCountry spain = GenericBuilder.of(FavoriteCountry::new)
                .with(FavoriteCountry::setCountryCode, "ES")
                .with(FavoriteCountry::setCreatedAt, LocalDateTime.now())
                .build();
        FavoriteCountry france = GenericBuilder.of(FavoriteCountry::new)
                .with(FavoriteCountry::setCountryCode, "FR")
                .with(FavoriteCountry::setCreatedAt, LocalDateTime.now())
                .build();
        when(repo.findAll()).thenReturn(List.of(spain, france));
        // when
        Set<String> result = service.getFavoriteCountryCodes();
        // then
        assertEquals(Set.of("ES", "FR"), result);
    }

    @Test
    void should_return_all_favorite_countries() {
        // given
        LocalDateTime createdAt = LocalDateTime.of(2026, Month.JANUARY, 15, 23, 59, 59);
        FavoriteCountry favorite = GenericBuilder.of(FavoriteCountry::new)
                .with(FavoriteCountry::setCountryCode, "ES")
                .with(FavoriteCountry::setCreatedAt, createdAt)
                .build();
        when(repo.findAll()).thenReturn(List.of(favorite));
        // when
        List<FavoriteCountryDto> result = service.getFavoriteCountries();
        // then
        FavoriteCountryDto dto = result.get(0);
        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals("ES", dto.countryCode()),
                () -> assertEquals(createdAt, dto.createdAt())
        );
    }
}
