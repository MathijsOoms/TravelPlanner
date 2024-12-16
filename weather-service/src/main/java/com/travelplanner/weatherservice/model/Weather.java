package com.travelplanner.weatherservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "weather")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String country;
    private String weatherDescription;
    private double temperature;
    private double precipitationChance;
    private double humidity;
    private double windSpeed;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
