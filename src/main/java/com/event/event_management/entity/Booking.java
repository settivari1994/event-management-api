package com.event.event_management.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String customerPhone;

    private Integer ticketCount;
    private Double totalPrice;

    private String paymentStatus;

    private LocalDateTime bookingTime;

    @ManyToOne
    private Event event;

    @ManyToOne
    private User agent;

    // Getters & Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getCustomerName() { return customerName; }

    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerPhone() { return customerPhone; }

    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public Integer getTicketCount() { return ticketCount; }

    public void setTicketCount(Integer ticketCount) { this.ticketCount = ticketCount; }

    public Double getTotalPrice() { return totalPrice; }

    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }

    public String getPaymentStatus() { return paymentStatus; }

    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public LocalDateTime getBookingTime() { return bookingTime; }

    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }

    public Event getEvent() { return event; }

    public void setEvent(Event event) { this.event = event; }

    public User getAgent() { return agent; }

    public void setAgent(User agent) { this.agent = agent; }
}