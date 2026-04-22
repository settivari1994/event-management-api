package com.event.event_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.event_management.entity.Booking;


public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	    List<Booking> findByAgentId(Long agentId);

	    List<Booking> findByAgentUsername(String username);
	
}