package com.event.event_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.event.event_management.entity.Booking;
import com.event.event_management.entity.User;
import com.event.event_management.repository.BookingRepository;
import com.event.event_management.repository.UserRepository;
import com.event.event_management.service.BookingService;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final UserRepository userRepository;
    @Autowired BookingRepository bookingRepository;

    public BookingController(BookingService bookingService,
                             UserRepository userRepository) {
        this.bookingService = bookingService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public Booking bookTicket(@RequestBody Map<String, Object> req,
                              Authentication authentication) {

        // ✅ GET USERNAME FROM TOKEN
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return bookingService.bookTicket(
                Long.valueOf(req.get("eventId").toString()),
                user.getId(), // ✅ FIXED HERE
                req.get("customerName").toString(),
                req.get("customerPhone").toString(),
                Integer.parseInt(req.get("ticketCount").toString())
        );
    }
    
    
    @PutMapping("/{id}/confirm")
    public Booking confirmPayment(@PathVariable Long id) {
        return bookingService.confirmPayment(id);
    }
    
    
    @GetMapping("/reports")
    public List<Booking> getAllBookingsSorted() {
        return bookingRepository.findAll();
    }
}