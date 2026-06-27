package com.mikepaskual.traveltracker.testdata;

import com.mikepaskual.traveltracker.client.dto.*;

import java.util.List;

public final class CountryResponseMother {

    private CountryResponseMother() {
        super();
    }

    public static CountryResponse spain() {
        return new CountryResponse(
                new NameResponse(
                        "Spain",
                        "Kingdom of Spain"),
                new CodeResponse(
                        "ES",
                        "ESP"),
                List.of(
                        new CapitalResponse(
                                "Madrid")),
                new FlagResponse(
                        "The flag of Spain is composed of three horizontal bands of red, yellow and red, " +
                                "with the yellow band twice the height of the red bands. In the yellow band is the " +
                                "national coat of arms offset slightly towards the hoist side of center.",
                        "https://flags.restcountries.com/v5/w640/es.png",
                        "https://flags.restcountries.com/v5/svg/es.svg"),
                "Europe"
        );
    }

    public static CountryResponse france() {
        return new CountryResponse(
                new NameResponse(
                        "France",
                        "French Republic"),
                new CodeResponse(
                        "FR",
                        "FRA"),
                List.of(
                        new CapitalResponse(
                                "Paris")),
                new FlagResponse(
                        "The flag of France is composed of three equal vertical bands of blue, white and red.",
                        "https://flags.restcountries.com/v5/w640/fr.png",
                        "https://flags.restcountries.com/v5/svg/fr.svg"),
                "Europe"
        );
    }
}
