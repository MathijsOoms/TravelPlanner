package com.travelplanner.travelservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TravelResponse {
    private Long id;
    private String startLocation;
    private String endLocation;
    private String transportType;
    private double price;
    private String duration;
    private String departureTime;
    private String arrivalTime;
}
