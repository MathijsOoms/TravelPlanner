package com.travelplanner.weatherservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse {
    private Long id;
    private String city;
    private String country;
    private String weatherDescription;
    private double temperature;
    private double precipitationChance;
    private double humidity;
    private double windSpeed;
    private LocalDateTime timestamp;
}
