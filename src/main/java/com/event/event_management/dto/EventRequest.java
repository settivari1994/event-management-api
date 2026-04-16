package com.event.event_management.dto;


import java.time.LocalDateTime;

public class EventRequest {
	
	private String name;
    private String description;
    private String location;
    private LocalDateTime eventDateTime;
    private Double price;
    private Integer totalTickets;
	private Integer availableTickets;
	private Long organizerId;
	
	
	public Integer getAvailableTickets() {
		return availableTickets;
	}

	public void setAvailableTickets(Integer availableTickets) {
		this.availableTickets = availableTickets;
	}

	
	public Long getOrganizerId() {
	    return organizerId;
	}

	public void setOrganizerId(Long organizerId) {
	    this.organizerId = organizerId;
	}

	
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public LocalDateTime getEventDateTime() {
		return eventDateTime;
	}
	public void setEventDateTime(LocalDateTime eventDateTime) {
		this.eventDateTime = eventDateTime;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getTotalTickets() {
		return totalTickets;
	}
	public void setTotalTickets(Integer totalTickets) {
		this.totalTickets = totalTickets;
	}


}