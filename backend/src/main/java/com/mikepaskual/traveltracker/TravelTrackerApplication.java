package com.mikepaskual.traveltracker;

import com.mikepaskual.traveltracker.config.RestCountriesProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RestCountriesProperties.class)
public class TravelTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelTrackerApplication.class, args);
	}

}
