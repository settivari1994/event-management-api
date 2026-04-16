package com.event.event_management.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private Double price;

    private Integer totalTickets;
    private Integer availableTickets;

    private LocalDateTime eventDate;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private User assignedAgent;

    // Getters & Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public Integer getTotalTickets() { return totalTickets; }

    public void setTotalTickets(Integer totalTickets) { this.totalTickets = totalTickets; }

    public Integer getAvailableTickets() { return availableTickets; }

    public void setAvailableTickets(Integer availableTickets) { this.availableTickets = availableTickets; }

    public LocalDateTime getEventDate() { return eventDate; }

    public void setEventDate(LocalDateTime eventDate) { this.eventDate = eventDate; }

    public User getAssignedAgent() { return assignedAgent; }

    public void setAssignedAgent(User assignedAgent) { this.assignedAgent = assignedAgent; }
}