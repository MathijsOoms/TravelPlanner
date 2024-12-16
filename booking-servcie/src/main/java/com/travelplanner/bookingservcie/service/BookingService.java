package com.travelplanner.bookingservcie.service;

import com.travelplanner.bookingservcie.dto.BookingRequest;
import com.travelplanner.bookingservcie.dto.BookingResponse;
import com.travelplanner.bookingservcie.model.Booking;
import com.travelplanner.bookingservcie.repository.BookingRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public void createBooking(BookingRequest bookingRequest) {
        Booking booking = Booking.builder()
                .customerName(bookingRequest.getCustomerName())
                .customerEmail(bookingRequest.getCustomerEmail())
                .destination(bookingRequest.getDestination())
                .startTime(bookingRequest.getStartTime())
                .endTime(bookingRequest.getEndTime())
                .numberOfPersons(bookingRequest.getNumberOfPersons())
                .price(bookingRequest.getPrice())
                .status(bookingRequest.getStatus())
                .build();

        bookingRepository.save(booking);
    }

    // Get all bookings
    public List<BookingResponse> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(this::mapToBookingResponse).toList();
    }

    // Get booking by ID
    public BookingResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with id: " + id));
        return mapToBookingResponse(booking);
    }

    // Update booking by ID
    public void updateBooking(Long id, BookingRequest bookingRequest) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with id: " + id));

        booking.setCustomerName(bookingRequest.getCustomerName());
        booking.setCustomerEmail(bookingRequest.getCustomerEmail());
        booking.setDestination(bookingRequest.getDestination());
        booking.setStartTime(bookingRequest.getStartTime());
        booking.setEndTime(bookingRequest.getEndTime());
        booking.setNumberOfPersons(bookingRequest.getNumberOfPersons());
        booking.setPrice(bookingRequest.getPrice());
        booking.setStatus(bookingRequest.getStatus());

        bookingRepository.save(booking);
    }

    // Delete booking by ID
    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with id: " + id));
        bookingRepository.delete(booking);
    }

    // Helper method to map Weather entity to WeatherResponse
    private BookingResponse mapToBookingResponse(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .customerName(booking.getCustomerName())
                .customerEmail(booking.getCustomerEmail())
                .destination(booking.getDestination()).startTime(booking.getStartTime())
                .endTime(booking.getEndTime())
                .numberOfPersons(booking.getNumberOfPersons())
                .price(booking.getPrice())
                .status(booking.getStatus())
                .build();
    }

    @PostConstruct
    public void seedData() {
        // Check if the database already has booking data
        if (bookingRepository.count() == 0) {
            Booking booking1 = Booking.builder()
                    .customerName("Alice Johnson")
                    .customerEmail("alice.johnson@example.com")
                    .destination("Rome, Italy")
                    .startTime(LocalDate.parse("2024-12-20"))
                    .endTime(LocalDate.parse("2024-12-27"))
                    .numberOfPersons(2)
                    .price(1200.00)
                    .status("Confirmed")
                    .build();

            Booking booking2 = Booking.builder()
                    .customerName("Bob Smith")
                    .customerEmail("bob.smith@example.com")
                    .destination("Tokyo, Japan")
                    .startTime(LocalDate.parse("2025-01-10"))
                    .endTime(LocalDate.parse("2025-01-20"))
                    .numberOfPersons(4)
                    .price(5000.00)
                    .status("Pending")
                    .build();

            Booking booking3 = Booking.builder()
                    .customerName("Charlie Davis")
                    .customerEmail("charlie.davis@example.com")
                    .destination("New York, USA")
                    .startTime(LocalDate.parse("2024-12-25"))
                    .endTime(LocalDate.parse("2025-01-01"))
                    .numberOfPersons(1)
                    .price(800.00)
                    .status("Cancelled")
                    .build();

            // Save all the bookings to the database
            bookingRepository.save(booking1);
            bookingRepository.save(booking2);
            bookingRepository.save(booking3);
        }
    }
}