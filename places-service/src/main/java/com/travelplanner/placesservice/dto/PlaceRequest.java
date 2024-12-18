package com.travelplanner.placesservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRequest {
    private String name;
    private String description;
    private String address;
    private double rating;
    private String image;
    private String type;
    private String status;
}
