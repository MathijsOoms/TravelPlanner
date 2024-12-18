package com.travelplanner.placesservice.service;

import com.travelplanner.placesservice.dto.PlaceRequest;
import com.travelplanner.placesservice.dto.PlaceResponse;
import com.travelplanner.placesservice.model.Place;
import com.travelplanner.placesservice.repository.PlaceRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    @PostConstruct
    public void loadData() {
        if (placeRepository.count() <= 0) {
            Place place = Place.builder()
                    .name("Stainless steel acid-resistant seamless tube OD 6 mm x 1 mm steel grade 316 / 316L 180cm")
                    .description("")
                    .address("123 Industrial Lane, Steel City")
                    .rating(4.5)
                    .image("https://example.com/images/steel_tube.jpg")
                    .type("Industrial Material")
                    .status("Available")
                    .build();

            Place place1 = Place.builder()
                    .name("Wooden beam 10 feet long")
                    .description("Faux Wood Beams - 10 ft. Length & 10 in. Width Cast from natural wood beams with surface textures and wood-grain detail")
                    .address("456 Timber Road, Woodville")
                    .rating(4.8)
                    .image("https://example.com/images/wooden_beam.jpg")
                    .type("Construction Material")
                    .status("Available")
                    .build();

            placeRepository.save(place);
            placeRepository.save(place1);
        }
    }

    public void createPlace(PlaceRequest placeRequest) {
        Place place = Place.builder()
                .name(placeRequest.getName())
                .description(placeRequest.getDescription())
                .address(placeRequest.getAddress())
                .rating(placeRequest.getRating())
                .image(placeRequest.getImage())
                .type(placeRequest.getType())
                .status(placeRequest.getStatus())
                .build();

        placeRepository.save(place);
    }


    public PlaceResponse getPlaceById(String id) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Place not found with id: " + id));
        return mapToPlaceResponse(place);
    }

    public List<PlaceResponse> getPlacesByType(String type) {
        List<Place> places = placeRepository.findByType(type);
        return places.stream().map(this::mapToPlaceResponse).toList();
    }

    public List<PlaceResponse> getAllPlaces() {
        List<Place> places = placeRepository.findAll();
        return places.stream().map(this::mapToPlaceResponse).toList();
    }

    public void updatePlace(String id, PlaceRequest placeRequest) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Place not found with id: " + id));
        place.setName(placeRequest.getName());
        place.setDescription(placeRequest.getDescription());
        place.setAddress(placeRequest.getAddress());
        place.setRating(placeRequest.getRating());
        place.setImage(placeRequest.getImage());
        place.setType(placeRequest.getType());
        place.setStatus(placeRequest.getStatus());

        placeRepository.save(place);
    }

    public void deletePlace(String id) {
        placeRepository.deleteById(id);
    }

    private PlaceResponse mapToPlaceResponse(Place place) {
        return PlaceResponse.builder()
                .id(place.getId())
                .name(place.getName())
                .description(place.getDescription())
                .address(place.getAddress())
                .rating(place.getRating())
                .image(place.getImage())
                .type(place.getType())
                .status(place.getStatus())
                .build();
    }
}