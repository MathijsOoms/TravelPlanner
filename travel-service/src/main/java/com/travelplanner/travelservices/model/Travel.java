package com.travelplanner.travelservices.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "travel")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String startLocation;
    private String endLocation;
    private String transportType;
    private double price;
    private String duration;
    private String departureTime;
    private String arrivalTime;
}
