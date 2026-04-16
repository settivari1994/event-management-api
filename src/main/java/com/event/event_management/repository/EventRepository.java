package com.event.event_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.event_management.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
	
    List<Event> findByAssignedAgentId(Long agentId);

}
