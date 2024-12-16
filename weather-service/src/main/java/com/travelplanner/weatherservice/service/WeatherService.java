package com.travelplanner.weatherservice.service;

import com.travelplanner.weatherservice.dto.WeatherRequest;
import com.travelplanner.weatherservice.dto.WeatherResponse;
import com.travelplanner.weatherservice.model.Weather;
import com.travelplanner.weatherservice.repository.WeatherRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherRepository weatherRepository;

    // Create new weather
    public void createWeather(WeatherRequest weatherRequest) {
        Weather weather = Weather.builder()
                .city(weatherRequest.getCity())
                .country(weatherRequest.getCountry())
                .weatherDescription(weatherRequest.getWeatherDescription())
                .temperature(weatherRequest.getTemperature())
                .precipitationChance(weatherRequest.getPrecipitationChance())
                .humidity(weatherRequest.getHumidity())
                .windSpeed(weatherRequest.getWindSpeed())
                .timestamp(weatherRequest.getTimestamp())
                .build();

        weatherRepository.save(weather);
    }

    // Get weather by city
    public List<WeatherResponse> getWeatherByCity(String city) {
        List<Weather> weathers = weatherRepository.findByCity(city);
        if (weathers.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No weather data found for city: " + city);
        }
        return weathers.stream().map(this::mapToWeatherResponse).toList();
    }

    // Get all weather data
    public List<WeatherResponse> getAllWeather() {
        List<Weather> weathers = weatherRepository.findAll();
        return weathers.stream().map(this::mapToWeatherResponse).toList();
    }

    // Get weather by ID
    public WeatherResponse getWeatherById(Long id) {
        Weather weather = weatherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Weather not found with id: " + id));
        return mapToWeatherResponse(weather);
    }

    // Update weather data by ID
    public void updateWeather(Long id, WeatherRequest weatherRequest) {
        Weather weather = weatherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Weather not found with id: " + id));

        weather.setCity(weatherRequest.getCity());
        weather.setCountry(weatherRequest.getCountry());
        weather.setWeatherDescription(weatherRequest.getWeatherDescription());
        weather.setTemperature(weatherRequest.getTemperature());
        weather.setPrecipitationChance(weatherRequest.getPrecipitationChance());
        weather.setHumidity(weatherRequest.getHumidity());
        weather.setWindSpeed(weatherRequest.getWindSpeed());
        weather.setTimestamp(weatherRequest.getTimestamp());

        weatherRepository.save(weather);
    }

    // Delete weather data by ID
    public void deleteWeather(Long id) {
        Weather weather = weatherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Weather not found with id: " + id));
        weatherRepository.delete(weather);
    }

    // Helper method to map Weather entity to WeatherResponse
    private WeatherResponse mapToWeatherResponse(Weather weather) {
        return WeatherResponse.builder()
                .id(weather.getId())
                .city(weather.getCity())
                .country(weather.getCountry())
                .weatherDescription(weather.getWeatherDescription())
                .temperature(weather.getTemperature())
                .precipitationChance(weather.getPrecipitationChance())
                .humidity(weather.getHumidity())
                .windSpeed(weather.getWindSpeed())
                .timestamp(weather.getTimestamp())
                .build();
    }

    // Method to load data into the database at startup
    @PostConstruct
    public void seedData() {
        // Check if the database already has weather data
        if (weatherRepository.count() == 0) {
            Weather weather1 = new Weather();
            weather1.setCity("Boechout");
            weather1.setCountry("Belgium");
            weather1.setWeatherDescription("Mostly cloudy");
            weather1.setTemperature(4.0);
            weather1.setPrecipitationChance(10.0);
            weather1.setHumidity(90.0);
            weather1.setWindSpeed(13.0);
            weather1.setTimestamp(LocalDateTime.parse("2024-12-14T15:30:25"));

            Weather weather2 = new Weather();
            weather2.setCity("Amsterdam");
            weather2.setCountry("Netherlands");
            weather2.setWeatherDescription("Sunny");
            weather2.setTemperature(24.0);
            weather2.setPrecipitationChance(0.0);
            weather2.setHumidity(80.0);
            weather2.setWindSpeed(15.0);
            weather2.setTimestamp(LocalDateTime.parse("2024-07-18T14:21:40"));

            Weather weather3 = new Weather();
            weather3.setCity("London");
            weather3.setCountry("United Kingdom");
            weather3.setWeatherDescription("Rainy");
            weather3.setTemperature(8.0);
            weather3.setPrecipitationChance(80.0);
            weather3.setHumidity(85.0);
            weather3.setWindSpeed(22.0);
            weather3.setTimestamp(LocalDateTime.parse("2024-11-15T15:00:00"));

            // Save weather data to database
            weatherRepository.save(weather1);
            weatherRepository.save(weather2);
            weatherRepository.save(weather3);

            System.out.println("Weather data successfully seeded into the database.");
        } else {
            System.out.println("Weather data already exists in the database.");
        }
    }
}