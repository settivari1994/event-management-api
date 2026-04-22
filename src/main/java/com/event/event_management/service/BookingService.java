package com.event.event_management.service;

import com.event.event_management.dto.*;
import com.event.event_management.entity.*;
import com.event.event_management.repository.BookingRepository;
import com.event.event_management.repository.TicketCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TicketCategoryRepository ticketCategoryRepository;

    // ==============================
    // CREATE BOOKING
    // ==============================
    @Transactional
    public BookingResponse createBooking(BookingRequest request) {

        // ✅ Validate request
        if (request.getSelections() == null || request.getSelections().isEmpty()) {
            throw new RuntimeException("No ticket categories selected");
        }

        // ==============================
        // CREATE BOOKING ENTITY
        // ==============================
        Booking booking = new Booking();
        booking.setCustomerName(request.getCustomerName());
        booking.setCustomerPhone(request.getCustomerPhone());
        booking.setPaymentMethod(request.getPaymentMethod());
        booking.setTransactionId(request.getTransactionId());
        booking.setPaymentStatus("PAID");
        booking.setBookingTime(LocalDateTime.now());

        // ==============================
        // FETCH ALL CATEGORIES
        // ==============================
        List<Long> categoryIds = request.getSelections()
                .stream()
                .map(CategorySelection::getCategoryId)
                .toList();

        Map<Long, TicketCategory> categoryMap =
                ticketCategoryRepository.findAllById(categoryIds)
                        .stream()
                        .collect(Collectors.toMap(TicketCategory::getId, c -> c));

        // ==============================
        // MERGE DUPLICATE SELECTIONS
        // ==============================
        Map<Long, Integer> mergedSelections = new HashMap<>();

        for (CategorySelection cs : request.getSelections()) {
            mergedSelections.merge(cs.getCategoryId(), cs.getQuantity(), Integer::sum);
        }

        List<BookingItem> items = new ArrayList<>();
        double totalAmount = 0;

        // ==============================
        // PROCESS EACH CATEGORY
        // ==============================
        for (Map.Entry<Long, Integer> entry : mergedSelections.entrySet()) {

            Long categoryId = entry.getKey();
            int quantity = entry.getValue();

            TicketCategory category = categoryMap.get(categoryId);

            // ❌ category not found
            if (category == null) {
                throw new RuntimeException("Category not found: " + categoryId);
            }

            // ❌ invalid quantity
            if (quantity <= 0) {
                throw new RuntimeException("Invalid quantity for category: " + category.getName());
            }

            // ❌ stock check
            if (category.getRemainingQuantity() < quantity) {
                throw new RuntimeException("Not enough tickets for: " + category.getName());
            }

            // ==============================
            // UPDATE STOCK
            // ==============================
            category.setRemainingQuantity(
                    category.getRemainingQuantity() - quantity
            );

            ticketCategoryRepository.save(category); // ✅ IMPORTANT FIX

            // ==============================
            // CREATE BOOKING ITEM
            // ==============================
            BookingItem item = new BookingItem();
            item.setCategoryId(category.getId());
            item.setCategoryName(category.getName());
            item.setPrice(category.getPrice());
            item.setQuantity(quantity);
            item.setBooking(booking);

            items.add(item);

            totalAmount += category.getPrice() * quantity;
        }

        // ==============================
        // FINALIZE BOOKING
        // ==============================
        booking.setItems(items);
        booking.setTotalAmount(totalAmount);

        Booking saved = bookingRepository.save(booking);

        return mapToResponse(saved);
    }

    // ==============================
    // GET ALL BOOKINGS
    // ==============================
    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ==============================
    // GET BOOKING BY ID
    // ==============================
    public BookingResponse getBookingById(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        return mapToResponse(booking);
    }

    // ==============================
    // ENTITY → RESPONSE MAPPER
    // ==============================
    private BookingResponse mapToResponse(Booking booking) {

        BookingResponse response = new BookingResponse();

        response.setBookingId(booking.getId());
        response.setCustomerName(booking.getCustomerName());
        response.setCustomerPhone(booking.getCustomerPhone());
        response.setTotalAmount(booking.getTotalAmount());
        response.setPaymentStatus(booking.getPaymentStatus());
        response.setPaymentMethod(booking.getPaymentMethod());
        response.setBookingTime(booking.getBookingTime());

        List<BookingItemResponse> items = Optional.ofNullable(booking.getItems())
                .orElse(Collections.emptyList())
                .stream()
                .map(item -> {
                    BookingItemResponse r = new BookingItemResponse();
                    r.setCategoryId(item.getCategoryId());
                    r.setCategoryName(item.getCategoryName());
                    r.setQuantity(item.getQuantity());
                    r.setPrice(item.getPrice());
                    r.setTotal(item.getPrice() * item.getQuantity());
                    return r;
                })
                .toList();

        response.setItems(items);

        return response;
    }
}