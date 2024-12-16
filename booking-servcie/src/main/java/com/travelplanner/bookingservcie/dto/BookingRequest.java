package com.travelplanner.bookingservcie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {
    private String customerName;
    private String customerEmail;
    private String destination;
    private LocalDate startTime;
    private LocalDate endTime;
    private int numberOfPersons;
    private double price;
    private String status;
}
