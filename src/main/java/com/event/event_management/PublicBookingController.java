package com.event.event_management;

import com.event.event_management.dto.BookingResponse;
import com.event.event_management.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/bookings")
@CrossOrigin(origins = "*")
public class PublicBookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getPublicBooking(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }
}