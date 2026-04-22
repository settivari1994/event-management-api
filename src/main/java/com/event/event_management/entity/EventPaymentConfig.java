package com.event.event_management.entity;

import jakarta.persistence.*;

@Entity
public class EventPaymentConfig {

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUpiId() {
		return upiId;
	}

	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String upiId;

    @OneToOne
    @JoinColumn(name = "event_id", unique = true)
    private Event event;

}