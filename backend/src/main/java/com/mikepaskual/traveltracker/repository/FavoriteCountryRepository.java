package com.mikepaskual.traveltracker.repository;

import com.mikepaskual.traveltracker.entity.FavoriteCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteCountryRepository extends JpaRepository<FavoriteCountry, String> {
}
