package com.event.event_management.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.event_management.entity.Booking;
import com.event.event_management.entity.Event;
import com.event.event_management.entity.User;
import com.event.event_management.repository.BookingRepository;
import com.event.event_management.repository.EventRepository;
import com.event.event_management.repository.UserRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Booking bookTicket(Long eventId, Long agentId,
                              String customerName,
                              String customerPhone,
                              int ticketCount) {

        Event event = eventRepository.findById(eventId).orElseThrow();

        if (event.getAvailableTickets() < ticketCount) {
            throw new RuntimeException("Not enough tickets");
        }

        //event.setAvailableTickets(event.getAvailableTickets() - ticketCount);

        Booking booking = new Booking();
        booking.setCustomerName(customerName);
        booking.setCustomerPhone(customerPhone);
        booking.setTicketCount(ticketCount);
        booking.setTotalPrice(ticketCount * event.getPrice());
        booking.setPaymentStatus("PENDING");
        booking.setBookingTime(LocalDateTime.now());
        booking.setEvent(event);

        User agent = userRepository.findById(agentId).orElseThrow();
        booking.setAgent(agent);

        eventRepository.save(event);

        return bookingRepository.save(booking);
    }
    
    
    @Transactional
    public Booking confirmPayment(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if ("PAID".equals(booking.getPaymentStatus())) {
            throw new RuntimeException("Payment already confirmed");
        }

        Event event = booking.getEvent();

        if (event.getAvailableTickets() < booking.getTicketCount()) {
            throw new RuntimeException("Tickets sold out");
        }

        // ✅ Deduct tickets ONLY after payment
        event.setAvailableTickets(
            event.getAvailableTickets() - booking.getTicketCount()
        );

        booking.setPaymentStatus("PAID");

        eventRepository.save(event);

        return bookingRepository.save(booking);
    }
}