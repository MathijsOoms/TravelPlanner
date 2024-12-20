package com.travelplanner.placesservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "places")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Place {
    private String id;
    private String name;
    private String description;
    private String address;
    private double rating;
    private String image;
    private String type;
    private String status;
}
