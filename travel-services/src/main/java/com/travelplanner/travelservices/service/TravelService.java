package com.travelplanner.travelservices.service;

import com.travelplanner.travelservices.dto.TravelRequest;
import com.travelplanner.travelservices.dto.TravelResponse;
import com.travelplanner.travelservices.model.Travel;
import com.travelplanner.travelservices.repository.TravelRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;

    public void createTravel(TravelRequest travelRequest) {
        Travel travel = Travel.builder()
                .startLocation(travelRequest.getStartLocation())
                .endLocation(travelRequest.getEndLocation())
                .transportType(travelRequest.getTransportType())
                .price(travelRequest.getPrice())
                .duration(travelRequest.getDuration())
                .departureTime(travelRequest.getDepartureTime())
                .arrivalTime(travelRequest.getArrivalTime())
                .build();

        travelRepository.save(travel);
    }

    public List<TravelResponse> getTravelOptions(String startLocation, String endLocation) {
        List<Travel> travels = travelRepository.findByStartLocationAndEndLocation(startLocation, endLocation);
        return travels.stream().map(this::mapToTravelResponse).toList();
    }

    public List<TravelResponse> getAllTravels() {
        List<Travel> travels = travelRepository.findAll();
        return travels.stream().map(this::mapToTravelResponse).toList();
    }

    public TravelResponse getTravelById(Long id) {
        Travel travel = travelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Travel not found with id: " + id));
        return mapToTravelResponse(travel);
    }

    public void updateTravel(Long id, TravelRequest travelRequest) {
        Travel travel = travelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Travel not found with id: " + id));

        travel.setStartLocation(travelRequest.getStartLocation());
        travel.setEndLocation(travelRequest.getEndLocation());
        travel.setTransportType(travelRequest.getTransportType());
        travel.setPrice(travelRequest.getPrice());
        travel.setDuration(travelRequest.getDuration());
        travel.setDepartureTime(travelRequest.getDepartureTime());
        travel.setArrivalTime(travelRequest.getArrivalTime());

        travelRepository.save(travel);
    }

    public void deleteTravel(Long id) {
        Travel travel = travelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Travel not found with id: " + id));
        travelRepository.delete(travel);
    }

    private TravelResponse mapToTravelResponse(Travel travel) {
        return TravelResponse.builder()
                .id(travel.getId())
                .startLocation(travel.getStartLocation())
                .endLocation(travel.getEndLocation())
                .transportType(travel.getTransportType())
                .price(travel.getPrice())
                .duration(travel.getDuration())
                .departureTime(travel.getDepartureTime())
                .arrivalTime(travel.getArrivalTime())
                .build();
    }

    @PostConstruct
    public void seedData() {
        if (travelRepository.count() == 0) {
            Travel travel1 = Travel.builder()
                    .startLocation("Brussels")
                    .endLocation("Paris")
                    .transportType("Train")
                    .price(45.50)
                    .duration("1h 22m")
                    .departureTime("2024-01-15T09:00:00")
                    .arrivalTime("2024-01-15T10:22:00")
                    .build();

            Travel travel2 = Travel.builder()
                    .startLocation("Amsterdam")
                    .endLocation("Berlin")
                    .transportType("Flight")
                    .price(120.00)
                    .duration("1h 15m")
                    .departureTime("2024-01-16T07:00:00")
                    .arrivalTime("2024-01-16T08:15:00")
                    .build();

            Travel travel3 = Travel.builder()
                    .startLocation("London")
                    .endLocation("Edinburgh")
                    .transportType("Bus")
                    .price(35.00)
                    .duration("8h 30m")
                    .departureTime("2024-01-17T08:00:00")
                    .arrivalTime("2024-01-17T16:30:00")
                    .build();

            travelRepository.save(travel1);
            travelRepository.save(travel2);
            travelRepository.save(travel3);

            System.out.println("Travel data successfully seeded into the database.");
        } else {
            System.out.println("Travel data already exists in the database.");
        }
    }
}
