package com.mikepaskual.traveltracker.exception;

public class CountryNotFoundException extends RuntimeException {

    public CountryNotFoundException(String countryCode) {
        super("Country '" + countryCode + "' not found.");
    }
}
