package com.mikepaskual.traveltracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "favorite_country")
public class FavoriteCountry {

    @Id
    private String countryCode;
    private LocalDateTime createdAt;

    /**
     * Constructor.
     */
    public FavoriteCountry() {
        super();
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
