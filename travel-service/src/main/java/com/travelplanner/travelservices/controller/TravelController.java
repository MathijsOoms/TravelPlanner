package com.travelplanner.travelservices.controller;

import com.travelplanner.travelservices.dto.TravelRequest;
import com.travelplanner.travelservices.dto.TravelResponse;
import com.travelplanner.travelservices.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travel")
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelService;

    // GET: Fetch travel options between locations
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TravelResponse> getTravelOptions(
            @RequestParam String from,
            @RequestParam String to) {
        return travelService.getTravelOptions(from, to);
    }

    // POST: Create a new travel route
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTravel(@RequestBody TravelRequest travelRequest) {
        travelService.createTravel(travelRequest);
    }

    // GET: Fetch travel route by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TravelResponse getTravelById(@PathVariable Long id) {
        return travelService.getTravelById(id);
    }

    // GET: Fetch all travel routes
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<TravelResponse> getAllTravels() {
        return travelService.getAllTravels();
    }

    // PUT: Update a travel route by ID
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTravel(@PathVariable Long id, @RequestBody TravelRequest travelRequest) {
        travelService.updateTravel(id, travelRequest);
    }

    // DELETE: Delete a travel route by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTravelRoute(@PathVariable Long id) {
        travelService.deleteTravel(id);
    }
}
